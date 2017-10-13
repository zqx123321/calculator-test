## Android计算机测试脚本
1、先在res目录下新建raw文件夹，把testdata.txt和result.txt两个文件复制进去。</br>
2、修改计算器按钮id命名格式：
```Java
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
```
显示TextView id命名为TxtDisplay。</br>
或者修改此测试脚本规定新的id名称。</br>
3、把androidTest下的ExampleInstrumentedTest.java替换为此目录下的ExampleInstrumentedTest.java，run即可。
