package com.example.com.mycalculator;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import static android.content.Context.MODE_PRIVATE;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private int passedCount=0;
    private String failReport="";

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);


    @Test
    public void Test() throws InterruptedException, IOException {
        Context appContext = InstrumentationRegistry.getTargetContext();
        InputStream inputStreamData = appContext.getResources().openRawResource(R.raw.testdata);
        String[] testdata = getString(inputStreamData).split("\n");
        InputStream inputStreamRes = appContext.getResources().openRawResource(R.raw.result);
        String[] result = getString(inputStreamRes).split("\n");
        for (int i = 0; i < testdata.length; i++) {
            String[] item = testdata[i].split(" ");
            try {
                for (int j = 1; j < item.length; j++) {
                    getButton(item[j]);
                }
                String display = getText(withId(R.id.TxtDisplay));
                if (display.equals(result[i])) {
                    passedCount++;
                }
                else {
                    failReport += ("测试样例：" + testdata[i] + "\\n");
                    failReport += ("正确结果：" + result[i] + "\\n");
                    failReport += ("实际结果：" + display + "\\n");
                    failReport += "\\n";
                }
            } catch (Exception e){
                failReport += ("测试样例：" + testdata[i] + "\\n");
                failReport += ("正确结果：" + result[i] + "\\n");
                failReport += ("实际结果：" + e.getMessage() + "\\n");
                failReport += "\\n";
            }
            onView(withId(R.id.BtnC)).perform(click());
        }
        System.out.println(failReport);
//        writeFile("ceshi.txt",failReport);
        assertEquals(testdata.length, passedCount,0);

    }

    public static void getButton(String btnName){
        switch (btnName){
            case "1":onView(withId(R.id.Btn1)).perform(click());break;
            case "2":onView(withId(R.id.Btn2)).perform(click());break;
            case "3":onView(withId(R.id.Btn3)).perform(click());break;
            case "4":onView(withId(R.id.Btn4)).perform(click());break;
            case "5":onView(withId(R.id.Btn5)).perform(click());break;
            case "6":onView(withId(R.id.Btn6)).perform(click());break;
            case "7":onView(withId(R.id.Btn7)).perform(click());break;
            case "8":onView(withId(R.id.Btn8)).perform(click());break;
            case "9":onView(withId(R.id.Btn9)).perform(click());break;
            case "0":onView(withId(R.id.Btn0)).perform(click());break;
            case ".":onView(withId(R.id.BtnPoint)).perform(click());break;
            case "+":onView(withId(R.id.BtnPlus)).perform(click());break;
            case "-":onView(withId(R.id.BtnMinus)).perform(click());break;
            case "*":onView(withId(R.id.BtnMultiply)).perform(click());break;
            case "/":onView(withId(R.id.BtnDivide)).perform(click());break;
            case "=":onView(withId(R.id.BtnEqual)).perform(click());break;
            case "C":onView(withId(R.id.BtnC)).perform(click());break;
            case "±":onView(withId(R.id.BtnSign)).perform(click());break;
            case "1/x":onView(withId(R.id.BtnReciprocal)).perform(click());break;
            case "√":onView(withId(R.id.BtnRooting)).perform(click());break;
            case "←":onView(withId(R.id.BtnBack)).perform(click());break;
            default:break;
        }
    }


    public static String getString(InputStream inputStream) {
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer("");
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String getText(final Matcher<View> matcher) {
        final String[] text = {null};
        onView(matcher).perform(new ViewAction() {
            //识别所操作的对象类型
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(TextView.class);
            }
            //视图操作的一个描述
            @Override
            public String getDescription() {
                return "getting text from a TextView";
            }
            //实际的一个操作，在之类我们就可以获取到操作的对象了。
            @Override
            public void perform(UiController uiController, View view) {
                TextView textView = (TextView)view;
                text[0] = textView.getText().toString();
            }
        });
        return text[0];
    }

    //写数据
    public void writeFile(String fileName,String writestr) throws IOException{
        Context appContext = InstrumentationRegistry.getTargetContext();
        try{
            FileOutputStream fout =appContext.openFileOutput(fileName, MODE_PRIVATE);
            byte [] bytes = writestr.getBytes();
            fout.write(bytes);
            fout.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
