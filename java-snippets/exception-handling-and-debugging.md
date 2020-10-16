---
title: Java 异常处理和调试
id: 12247
categories:
  - Java
date: 2015-06-03 01:45:30
tags:
---

## Try/Catch

![TryCatch](http://www.bihell.com/wp-content/uploads/2015/05/TryCatch.png)
<pre class="lang:java decode:true">public class Main {

    public static void main(String[] args) {
        try {
            String[] strings = {"Welcome!"};
            System.out.println(strings[1]);
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("There was an error");
        }
        System.out.println("The application is still running!");
    }
}</pre>

##  Throws

<pre class="lang:java decode:true">public class Main {

    public static void main(String[] args) {
        try {
            getArrayItem();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array item was out of bounds");
        }
    }

    private static void getArrayItem()
    throws ArrayIndexOutOfBoundsException{
        String[] strings = {"Welcome!"};
        System.out.println(strings[1]);
    }

}</pre>

## 命令行调试

样例代码:
<pre class="lang:java decode:true">public class DebugDemo {
	public static void main(String[] args) 
	{
        String message = args[0];
		System.out.println(message);
	}
}</pre>
编译程序:
<pre class="lang:default decode:true ">javac DebugDemo.java -g</pre>
初始化jdb:
<pre class="lang:default decode:true ">jdb DebugDemo "Welcome to BiHell.com" -Xdebug -Xrunjdwp:transport=st_socket,server=y,address=8000,suspendy=y</pre>
在DebugDemo类第五行暂停
<pre class="lang:default decode:true ">stop at DebugDemo:5</pre>
输入run 开始运行程序
<pre class="lang:default decode:true">run</pre>
如图所示,断电在第五行 System.out.println(message);

![java-debug-run](http://www.bihell.com/wp-content/uploads/2015/06/java-debug-run.png)

打list 可以显示我们所有的代码:

![java-debug-list](http://www.bihell.com/wp-content/uploads/2015/06/java-debug-list.png)

此时我们可以打印出我们接受的变量,如:
<pre class="lang:java decode:true">print args[0]
print message
cont
</pre>
![java-debug-value](http://www.bihell.com/wp-content/uploads/2015/06/java-debug-value.png)