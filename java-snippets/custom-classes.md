## Calculator2 的自定义类版 ([Java基本语句与控制流](http://www.bihell.com/exploring-syntax-and-flow/))

<span class="lang:default decode:true  crayon-inline">Calculator2.java</span>
<pre class="lang:java decode:true">public class Calculator2 {

    public static void main(String[] args) {
        String s1 = MathHelper.getInput("Enter a numeric value: ");
        String s2 = MathHelper.getInput("Enter a numeric value: ");
        String op = MathHelper.getInput("Enter 1=Add, 2=Subtract , 3=Multiply, 4=Divide : ");

        int opInt = Integer.parseInt(op);
        double result = 0;

        switch (opInt){
            case  1:
                 result = SimpleMath.addValues(s1, s2);
                break;
            case  2:
                 result = SimpleMath.subtractValues(s1, s2);
                break;
            case  3:
                 result = SimpleMath.multiplyValues(s1, s2);
                break;
            case  4:
                 result = SimpleMath.divideValues(s1, s2);
                break;
            default:
                System.out.println("You entered an incorrect value:");
                return;
        }
        System.out.println("The answer is " + result);
    }
}</pre>
<span class="lang:default decode:true  crayon-inline">MathHelper.java</span>
<pre class="lang:java decode:true ">import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Haseo on 5/31/2015.
 */
public class MathHelper {
    public static String getInput(String prompt) {
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
<span class="lang:default decode:true  crayon-inline ">SimpleMath.java</span>
<pre class="lang:java decode:true">public class SimpleMath {

    public static double addValues(String s1, String s2) {
        double d1 = Double.parseDouble(s1);
        double d2 = Double.parseDouble(s2);
        return d1 + d2;
    }

    public static double subtractValues(String s1, String s2) {
        double d1 = Double.parseDouble(s1);
        double d2 = Double.parseDouble(s2);
        return d1 - d2;
    }

    public static double multiplyValues(String s1, String s2) {
        double d1 = Double.parseDouble(s1);
        double d2 = Double.parseDouble(s2);
        return d1 * d2;
    }

    public static double divideValues(String s1, String s2) {
        double d1 = Double.parseDouble(s1);
        double d2 = Double.parseDouble(s2);
        return d1 / d2;
    }
}</pre>

##  组织Java类

![Organizing](http://www.bihell.com/wp-content/uploads/2015/05/Organizing.png)

##  Instance Methods (实例方法,对象)

参考 [Difference between Static methods and Instance methods](http://stackoverflow.com/questions/11993077/difference-between-static-methods-and-instance-methods)

<span class="lang:java decode:true  crayon-inline">Main.java</span>
<pre class="lang:java decode:true">package com.bihell.olivepress;

import com.bihell.olivepress.olives.Olive;
import com.bihell.olivepress.press.OlivePress;

public class Main {

    public static void main(String[] args){
        Olive olive = new Olive();
        olive.crush();

        Olive[] olives = {new Olive(),new Olive(),new Olive()};
        OlivePress press = new OlivePress();
        press.getOil(olives);
    }
}</pre>
<span class="lang:default decode:true  crayon-inline">Olive.java</span>
<pre class="lang:java decode:true ">package com.bihell.olivepress.olives;

public class Olive {

    public void crush()  {
        System.out.println("ouch!");
    }
}</pre>
<span class="lang:default decode:true  crayon-inline">OlivePress.java</span>
<pre class="lang:java decode:true ">package com.bihell.olivepress.press;

import com.bihell.olivepress.olives.Olive;

public class OlivePress {
    public void getOil(Olive[] olives) {
        for (Olive olive:olives) {
            olive.crush();
        }
    }
}</pre>
在Instance 变量中存储数据

<span class="lang:default decode:true  crayon-inline ">Main.java</span>
<pre class="lang:java decode:true">package com.bihell.olivepress;

import com.bihell.olivepress.olives.Olive;
import com.bihell.olivepress.press.OlivePress;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args){

        ArrayList&lt;Olive&gt; olives = new ArrayList&lt;Olive&gt;();

        Olive olive;

        olive=new Olive();
        System.out.println(olive.name);
        olives.add(olive);

        olive = new Olive();
        System.out.println(olive.name);
        olives.add(olive);

        olive = new Olive();
        System.out.println(olive.name);
        olives.add(olive);

        OlivePress press = new OlivePress();
        press.getOil(olives);
    }
}</pre>
<span class="lang:default decode:true  crayon-inline ">Olive.java</span>
<pre class="lang:java decode:true">package com.bihell.olivepress.olives;

public class Olive {

    public String name = "Kalamata";
    public String flavow = "Grassy";
    public long color = 0x000000;
    public int oil = 3;

    public int crush()  {
        System.out.println("ouch!");
        return  oil;
    }
}</pre>
<span class="lang:default decode:true  crayon-inline ">OlivePress.java</span>
<pre class="lang:java decode:true ">import com.bihell.olivepress.olives.Olive;

import java.util.ArrayList;

public class OlivePress {
    public void getOil(ArrayList&lt;Olive&gt; olives) {
        int oil = 0;

        for (Olive olive:olives) {
           oil += olive.crush();
        }

        System.out.println("You got "+ oil+" units of oil");
    }
}</pre>

## Constructor Method(构造方法)

<span class="lang:default decode:true  crayon-inline">Main.java</span>
<pre class="lang:java decode:true">package com.bihell.olivepress;

import com.bihell.olivepress.olives.Olive;
import com.bihell.olivepress.press.OlivePress;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args){

        ArrayList&lt;Olive&gt; olives = new ArrayList&lt;Olive&gt;();

        Olive olive;

        olive=new Olive(2);
        System.out.println(olive.name);
        olives.add(olive);

        olive = new Olive(1);
        System.out.println(olive.name);
        olives.add(olive);

        olive = new Olive(2);
        System.out.println(olive.name);
        olives.add(olive);

        OlivePress press = new OlivePress();
        press.getOil(olives);
    }
}</pre>
<span class="lang:default decode:true  crayon-inline ">Olive.java</span>
<pre class="lang:java decode:true ">package com.bihell.olivepress.olives;

public class Olive {

    public String name = "Kalamata";
    public String flavow = "Grassy";
    public long color = 0x000000;
    public int oil = 3;

    //构造方法
    public Olive(){
        System.out.println("Constructor of "+ this.name);
    }
    //Alt+insert 可以自动生成构造函数
    public Olive(int oil) {
        this.oil = oil;
    }

    public int crush()  {
        System.out.println("ouch!");
        return  oil;
    }
}</pre>
<span class="lang:default decode:true  crayon-inline">OlivePress.java</span>
<pre class="lang:java decode:true">package com.bihell.olivepress.press;

import com.bihell.olivepress.olives.Olive;

import java.util.ArrayList;

public class OlivePress {
    //为了让代码更清晰,可以习惯新的建立一个构造函数,即使什么都不做.
    public OlivePress() {
    }

    public void getOil(ArrayList&lt;Olive&gt; olives) {
        int oil = 0;

        for (Olive olive:olives) {
           oil += olive.crush();
        }

        System.out.println("You got "+ oil+" units of oil");
    }
}</pre>

##  Getter and Setter

按下Alt+Insert 可以自动生成Getter和Setter 我们选择oil

<span class="lang:default decode:true  crayon-inline ">Main.java</span>
<pre class="lang:java decode:true ">package com.bihell.olivepress;

import com.bihell.olivepress.olives.Olive;
import com.bihell.olivepress.press.OlivePress;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args){

        ArrayList&lt;Olive&gt; olives = new ArrayList&lt;Olive&gt;();

        Olive olive;

        olive=new Olive(2);
        System.out.println(olive.name);
        olives.add(olive);

        olive = new Olive(1);
        System.out.println(olive.name);
        olives.add(olive);

        olive = new Olive(2);
        System.out.println(olive.name);
        olives.add(olive);

        OlivePress press = new OlivePress();
        press.getOil(olives);

        System.out.println("You got "+ press.getTotalOil()+" units of oil");

        press.getOil(olives);

        System.out.println("You got "+ press.getTotalOil()+" units of oil");

    }
}</pre>
<span class="lang:default decode:true  crayon-inline ">Olive.java</span>
<pre class="lang:java decode:true ">package com.bihell.olivepress.olives;

public class Olive {

    public String name = "Kalamata";
    public String flavow = "Grassy";
    public long color = 0x000000;

    //利用Alt+Insert自动生成 getter and setter 
    private int oil = 3;

    public int getOil() {
        return oil;
    }

    public void setOil(int oil) {
        this.oil = oil;
    }

    //构造方法
    public Olive(){
        System.out.println("Constructor of "+ this.name);
    }
    //Alt+insert 可以自动生成构造函数
    public Olive(int oil) {
        setOil(oil);
    }

    public int crush()  {
        System.out.println("ouch!");
        return  oil;
    }
}</pre>
<span class="lang:default decode:true  crayon-inline ">OlivePress.java</span>
<pre class="lang:java decode:true ">package com.bihell.olivepress.press;

import com.bihell.olivepress.olives.Olive;

import java.util.ArrayList;

public class OlivePress {

    private int totalOil=0;

    public int getTotalOil() {
        return totalOil;
    }

    private void setTotalOil(int totalOil) {
        this.totalOil += totalOil;
    }

    public OlivePress() {
    }

    public void getOil(ArrayList&lt;Olive&gt; olives) {
        int oil = 0;

        for (Olive olive:olives) {
            oil += olive.crush();
        }

        setTotalOil(oil);

    }
}</pre>
&nbsp;