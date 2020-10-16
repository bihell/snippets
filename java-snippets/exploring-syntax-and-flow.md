## **IF 语句**

<pre class="lang:java decode:true">public class Main {
    public static void main(String[] args) {
        int monthNumber = 5;

        if (monthNumber &gt;= 1 &amp;&amp; monthNumber &lt;=3) {
            System.out.println("You're in Quarter 1");
        }
        else if (monthNumber &gt;= 4 &amp;&amp; monthNumber &lt;=6) {
            System.out.println("You're in Quarter 2");
        }
        else  {
            System.out.println("You're not in the first half of the year!");
        }

        String month ="February";
        if (month.equals("February")) {
            System.out.println("It's the second month!");
        }
    }
}</pre>

## **Switch语句(int)**

<pre class="lang:java decode:true">import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SwitchWithInts {

    public static void main(String[] args) {
        String input = getInput("Enter a number between 1 and 12: ");
        int month = Integer.parseInt(input); //只所以转换成Int是因为在Java 6.0的时候 switch 语句只能用在integers,shorts,bytes,以及Enums.

        switch (month) {
            case 1:
                System.out.println("The month is January");
                break; //如果去掉break,语句会继续运行下去
            case 2:
                System.out.println("The month is February");
                break;
            case 3:
                System.out.println("The month is March");
                break;
            default:
                System.out.println("You chose another month");
                break;
        }
    }

    private static String getInput(String prompt) {
        BufferedReader stdin = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.print(prompt);
        System.out.flush();

        try {
            return stdin.readLine();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

}</pre>

## **Switch**语句(枚举)

新建一个Month 类
<pre class="lang:java decode:true">/**
 * Created by Haseo on 5/30/2015.
 */
public enum  Month {
    JANUARY,FEBRUARY,MARCH;
}</pre>
修改语句
<pre class="lang:java decode:true">public class SwitchWithEnums {

    public static void main(String[] args) {

        Month month = Month.FEBRUARY;

        switch(month) {
            case JANUARY:
                System.out.println("The month first month");
                break;
            case FEBRUARY:
                System.out.println("The month second month");
                break;
            case MARCH:
                System.out.println("The month third month");
        }
    }
}</pre>

## **循环**

<pre class="lang:java decode:true">public class Main {

	static private String[] months =
		{"January", "February", "March",
		"April", "May", "June",
		"July", "August", "September",
		"October", "November", "December"};

    public static void main(String[] args) {
        for (int i = 0; i &lt;months.length ; i++) {
            System.out.println(months[i]);
        }
    //for each
        for (String month:months){
            System.out.println(month);
        }

        int counter = 0;
        while (counter &lt; months.length){
            System.out.println(months[counter]);
            counter++;
        }

       do {
            System.out.println(months[counter]);
            counter++;
        }  while (counter &lt; months.length);

    }
}</pre>

## **重构方法**

<pre class="lang:java decode:true">public class Main {

    public static void main(String[] args) {
        doSomething();
        int top = 10;
        for (int i = 0; i &lt;top ; i++) {
            System.out.println("the value is "+i);
      }
    }

    private static void  doSomething (){
        System.out.println("This method has been called");
    }
}</pre>
选中需要重构的语句块，然后在菜单中重构为方法

![Refactor2Method](http://www.bihell.com/wp-content/uploads/2015/05/Refactor2Method.jpg)

结果
<pre class="lang:default decode:true">public class Main {

    public static void main(String[] args) {
        doSomething();
        loopMe();
    }

    private static void loopMe() {
        int top = 10;
        for (int i = 0; i &lt;top ; i++) {
            System.out.println("the value is "+i);
        }
    }

    private static void  doSomething (){
        System.out.println("This method has been called");
    }
}</pre>
** 重构方法(带参数)**

以我们前面的计算器为例([<span style="color: #333333;">Java开发环境安装及基础语句</span>](http://www.bihell.com/java-jdk-setup/)),选择代码段,然后重构

![MethodArg](http://www.bihell.com/wp-content/uploads/2015/05/MethodArg.png)

结果如下
<pre class="lang:java decode:true">    public static void main(String[] args) {
        String s1 = getInput("Enter a numeric value: ");
        String s2 = getInput("Enter a numeric value: ");

        double result = addTwoValues(s1, s2);

        System.out.println("The answer is " + result);
    }

    private static double addTwoValues(String s1, String s2) {
        double d1 = Double.parseDouble(s1);
        double d2 = Double.parseDouble(s2);
        return d1 + d2;
    }</pre>

## ** 定义一个可以无限传入参数的方法**

<pre class="lang:java decode:true">import java.io.*;

public class Calculator {

    public static void main(String[] args) {
        String s1 = getInput("Enter a numeric value: ");
        String s2 = getInput("Enter a numeric value: ");

        double result = addTwoValues(s1, s2);

        System.out.println("The answer is " + result);

        double resultOfMultiple =addMultipleValues(1,2,3,4,5);
        System.out.println("The answer from multiple values is " + resultOfMultiple);
    }

    private static double addTwoValues(String s1, String s2) {
        double d1 = Double.parseDouble(s1);
        double d2 = Double.parseDouble(s2);
        return d1 + d2;
    }

    private static String getInput(String prompt) {
        BufferedReader stdin = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.print(prompt);
        System.out.flush();

        try {
            return stdin.readLine();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    //定义一个可以无限传入参数的方法
    private static double addMultipleValues(double... values) {
        double result = 0d;
        for (double d : values) {
            result += d;
        }
        return result;
    }
}</pre>

## **重载**

<pre class="lang:java decode:true  ">public class Overloading {
    public static void main(String[] args){

        int value1 = 5 ;
        int value2 = 10 ;
        int value3 = 15;

        int result = addValues(value1,value2,value3);
        System.out.println("The result is " + result);

        String string1 = "10";
        String string2 = "25";
        int result2 = addValues(string1,string2);
        System.out.println("The result is "+ result2);
    }

    private static int addValues(int int1,int int2){
        return int1 + int2;
    }

    private static int addValues(int int1,int int2, int int3){
        return int1+int2+int3;
    }

    private static int addValues(String val1,String val2 ){
        int value1 = Integer.parseInt(val1);
        int value2 = Integer.parseInt(val2);
        return value1+value2;
    }
}</pre>

## 简单计算器V2

<pre class="lang:java decode:true ">import java.io.*;

public class Calculator2 {

    public static void main(String[] args) {
        String s1 = getInput("Enter a numeric value: ");
        String s2 = getInput("Enter a numeric value: ");
        String op = getInput("Enter 1=Add, 2=Subtract , 3=Multiply, 4=Divide : ");

        int opInt = Integer.parseInt(op);
        double result = 0;

        switch (opInt){
            case  1:
                 result = addValues(s1,s2);
                break;
            case  2:
                 result = subtractValues(s1,s2);
                break;
            case  3:
                 result = multiplyValues(s1,s2);
                break;
            case  4:
                 result = divideValues(s1,s2);
                break;
            default:
                System.out.println("You entered an incorrect value:");
                return;

        }

        System.out.println("The answer is " + result);
    }

    private static double addValues(String s1, String s2) {
        double d1 = Double.parseDouble(s1);
        double d2 = Double.parseDouble(s2);
        return d1 + d2;
    }

    private static double subtractValues(String s1, String s2) {
        double d1 = Double.parseDouble(s1);
        double d2 = Double.parseDouble(s2);
        return d1 - d2;
    }

    private static double multiplyValues(String s1, String s2) {
        double d1 = Double.parseDouble(s1);
        double d2 = Double.parseDouble(s2);
        return d1 * d2;
    }

    private static double divideValues(String s1, String s2) {
        double d1 = Double.parseDouble(s1);
        double d2 = Double.parseDouble(s2);
        return d1 / d2;
    }
    private static String getInput(String prompt) {
        BufferedReader stdin = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.print(prompt);
        System.out.flush();

        try {
            return stdin.readLine();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

}</pre>

## **参考资料**

http://docs.oracle.com/javase/8/docs/api/