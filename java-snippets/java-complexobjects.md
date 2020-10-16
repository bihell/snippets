## **String 类型**

<pre class="lang:java decode:true ">public class ComplexStrings {
    public static  void main(String[] args){
        String s1= "Welcome to California!";
        String s2 = new String("Welcome to California!");

        //有些情况双等于号的结果会不同. 如下虽然s1和s2是同样的字符串,但是比较以后输出的是不匹配
        if (s1==s2){
            System.out.println("They match!");
        }
        else {
            System.out.println("They don't match!");
        }

        //使用equals方法比较  ,如果要忽略大小写可以用equalsIgnoreCase()
        if (s1.equals(s2)){
            System.out.println("They match!");
        }
        else {
            System.out.println("They don't match!");
        }

        char[] chars = s1.toCharArray();
        for (char c:chars){
            System.out.println(c);
        }
            }
}</pre>
String值的变动
<pre class="lang:java decode:true">public class StringBuilderDemo {

    public static void main(String[] args) {
        String s1 = "Welcome";
        //这里看似替换了s1的值,其实并不是这样.String的值是不变的,这里只是丢弃原有的然后新建一个实例
        s1 = s1 + "to California!";
        System.out.println(s1);

        //使用 StringBuilder() 减少字符替换带来的内存损失
        StringBuilder sb = new StringBuilder(s1);
        sb.append(" to California!");

        System.out.println(sb);
    }
}</pre>
<pre class="lang:java decode:true ">public class Main {

    public static void main(String[] args) {

        String s1 = "Welcome to California!";

        System.out.println("Length of string: "+ s1.length());

        int pos = s1.indexOf("California");
        System.out.println("Position of California: "+pos);

        String sub = s1.substring(pos);
        System.out.println(sub);

        String s2 = "Welcome!       ";
        int len1  =s2.length();
        System.out.println(len1);
        String s3 = s2.trim();
        System.out.println(s3.length());
    }

}</pre>

## Date

<pre class="lang:default decode:true">import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {
        Date d = new Date();
        System.out.println(d);

        GregorianCalendar gc = new GregorianCalendar(2009,1,15 );
        gc.add(GregorianCalendar.DATE,1);
        Date d2 = gc.getTime();

        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
        String sd = df.format(d2);
        System.out.println(sd);
    }
}</pre>

## Arrays,List,HashMap

<pre class="lang:java decode:true  ">import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        int[] a1 = new int[3];
        for (int i = 0; i &lt; a1.length; i++) {
            System.out.println(a1[i]);
        }

        int a2[] = new int[3];
        for (int i = 0; i &lt; a2.length; i++) {
            System.out.println(a2[i]);
        }

        int[] a3 = {3, 6, 9};
        for (int i = 0; i &lt; a3.length; i++) {
            System.out.println(a3[i]);
        }

        System.out.println("The value of the first item is "  + a3[0]);

        //two-dimensional arrays
        String[][] states = new String[3][2];
        states[0][0]="California";
        states[0][1]="Sacramento";
        states[1][0]="Oregon";
        states[1][1]="Salem";
        states[2][0]="Washington";
        states[2][1]="Olympia";

        for (int i = 0; i &lt; states.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j &lt;states[i].length ; j++) {
                if (j==0) {
                    sb.append("The capitol of ");
                }
                else {
                    sb.append(" is ");
                }
                sb.append(states[i][j]);
            }
            System.out.println(sb);
        }

        //Managing resizable arrays with ArrayList
        ArrayList &lt;String&gt; list = new ArrayList&lt;String&gt;();

        list.add("California");
        list.add("Oregon");
        list.add("Washington");

        System.out.println(list);
        list.add("Alaska");
        System.out.println(list);

        list.remove(0);
        System.out.println(list);

        String state = list.get(1);
        System.out.println("The second state is "+ state);

        int pos = list.indexOf("Alaska");
        System.out.println("Alaska is at position "+pos);

        //Managing unordered data with HashMap
        HashMap&lt;String,String&gt; map = new HashMap&lt;String,String&gt;();

        map.put("California","Sacramento");
        map.put("Oregon","Salem");
        map.put("Washington","Olympia");
        System.out.println(map);

        map.put("Alaska","Juneau");
        System.out.println(map);

        String cap = map.get("Oregon");
        System.out.println("The capitol of Oregon is "+cap);

        map.remove("California");
        System.out.println(map);

    }
}</pre>
以更高雅的方式循环读取List和Map
<pre class="lang:java decode:true ">import java.util.*;

public class Main {

    public static void main(String[] args) {

        //Managing resizable arrays with ArrayList
        ArrayList &lt;String&gt; list = new ArrayList&lt;String&gt;();
        list.add("California");
        list.add("Oregon");
        list.add("Washington");
        System.out.println(list);
        ListIterator&lt;String&gt; listIterator = list.listIterator();
        while (listIterator.hasNext()){
            String value = listIterator.next();
            System.out.println(value);
        }

        //Managing unordered data with HashMap
        HashMap&lt;String,String&gt; map = new HashMap&lt;String,String&gt;();
        map.put("California","Sacramento");
        map.put("Oregon","Salem");
        map.put("Washington","Olympia");
        System.out.println(map);
        Set&lt;String&gt; keys = map.keySet();
        Iterator&lt;String&gt; iterator = keys.iterator();
        while (iterator.hasNext()){
            String value = iterator.next();
            System.out.println("The capitol of " +value+" is "+map.get(value));
        }
    }
}</pre>
&nbsp;