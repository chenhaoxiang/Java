<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>【BeanUtils】自己写的一个BeanUtils-代码方法详解</title>
<link rel="stylesheet" href="https://stackedit.io/res-min/themes/base.css" />
<script type="text/javascript" src="https://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS_HTML"></script>
</head>
<body><div class="container"><blockquote cite="陈浩翔">
<p>转载请注明出处：<a href="http://blog.csdn.net/qq_26525215"><font color="green">http://blog.csdn.net/qq_26525215</font></a><br><br>
本文源自<strong>【<a href="http://blog.csdn.net/qq_26525215" target="_blank">大学之旅_谙忆的博客</a>】</strong></p>
</blockquote>

<p>BeanUtils工具包是由Apache公司所开发，主要是方便程序员对Bean类能够进行简便的操作。</p>

<p>在这里，不讲解如何使用apache的BeanUtils工具，而是我们自己写底层，自己利用类反射来实现BeanUtils的功能。 <br>
需要先学习类反射！</p>

<p>通过给定bean对象的类，和封装的Map对象，返回出一个bean对象。</p>

<h1 id="准备bean对象">准备bean对象:</h1>

<p>这里准备了User类和Book类:</p>



<h2 id="user">User</h2>



<pre class="prettyprint"><code class=" hljs java"><span class="hljs-keyword">package</span> cn.hncu.domain;

<span class="hljs-javadoc">/**
 *<span class="hljs-javadoctag"> @author</span> 陈浩翔
 *
 * 2016-8-25
 */</span>
<span class="hljs-keyword">public</span> <span class="hljs-class"><span class="hljs-keyword">class</span> <span class="hljs-title">User</span> {</span>
    <span class="hljs-keyword">private</span> String uuid;
    <span class="hljs-keyword">private</span> String name;
    <span class="hljs-keyword">private</span> <span class="hljs-keyword">int</span> age;
    <span class="hljs-keyword">public</span> String <span class="hljs-title">getUuid</span>() {
        <span class="hljs-keyword">return</span> uuid;
    }
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">setUuid</span>(String uuid) {
        <span class="hljs-keyword">this</span>.uuid = uuid;
    }
    <span class="hljs-keyword">public</span> String <span class="hljs-title">getName</span>() {
        <span class="hljs-keyword">return</span> name;
    }
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">setName</span>(String name) {
        <span class="hljs-keyword">this</span>.name = name;
    }
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">int</span> <span class="hljs-title">getAge</span>() {
        <span class="hljs-keyword">return</span> age;
    }
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">setAge</span>(<span class="hljs-keyword">int</span> age) {
        <span class="hljs-keyword">this</span>.age = age;
    }
    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">int</span> <span class="hljs-title">hashCode</span>() {
        <span class="hljs-keyword">final</span> <span class="hljs-keyword">int</span> prime = <span class="hljs-number">31</span>;
        <span class="hljs-keyword">int</span> result = <span class="hljs-number">1</span>;
        result = prime * result + ((uuid == <span class="hljs-keyword">null</span>) ? <span class="hljs-number">0</span> : uuid.hashCode());
        <span class="hljs-keyword">return</span> result;
    }
    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">boolean</span> <span class="hljs-title">equals</span>(Object obj) {
        <span class="hljs-keyword">if</span> (<span class="hljs-keyword">this</span> == obj)
            <span class="hljs-keyword">return</span> <span class="hljs-keyword">true</span>;
        <span class="hljs-keyword">if</span> (obj == <span class="hljs-keyword">null</span>)
            <span class="hljs-keyword">return</span> <span class="hljs-keyword">false</span>;
        <span class="hljs-keyword">if</span> (getClass() != obj.getClass())
            <span class="hljs-keyword">return</span> <span class="hljs-keyword">false</span>;
        User other = (User) obj;
        <span class="hljs-keyword">if</span> (uuid == <span class="hljs-keyword">null</span>) {
            <span class="hljs-keyword">if</span> (other.uuid != <span class="hljs-keyword">null</span>)
                <span class="hljs-keyword">return</span> <span class="hljs-keyword">false</span>;
        } <span class="hljs-keyword">else</span> <span class="hljs-keyword">if</span> (!uuid.equals(other.uuid))
            <span class="hljs-keyword">return</span> <span class="hljs-keyword">false</span>;
        <span class="hljs-keyword">return</span> <span class="hljs-keyword">true</span>;
    }
    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> String <span class="hljs-title">toString</span>() {
        <span class="hljs-keyword">return</span> <span class="hljs-string">"User [uuid="</span> + uuid + <span class="hljs-string">", name="</span> + name + <span class="hljs-string">", age="</span> + age + <span class="hljs-string">"]"</span>;
    }

}
</code></pre>



<h2 id="book">Book</h2>



<pre class="prettyprint"><code class=" hljs java"><span class="hljs-keyword">package</span> cn.hncu.domain;

<span class="hljs-javadoc">/**
 *<span class="hljs-javadoctag"> @author</span> 陈浩翔
 *
 * 2016-8-25
 */</span>
<span class="hljs-keyword">public</span> <span class="hljs-class"><span class="hljs-keyword">class</span> <span class="hljs-title">Book</span> {</span>
    <span class="hljs-keyword">private</span> String uuid;
    <span class="hljs-keyword">private</span> String name;
    <span class="hljs-keyword">private</span> <span class="hljs-keyword">double</span> inPrice;
    <span class="hljs-keyword">private</span> <span class="hljs-keyword">double</span> outPrice;
    <span class="hljs-keyword">private</span> <span class="hljs-keyword">int</span> num;
    <span class="hljs-keyword">public</span> String <span class="hljs-title">getUuid</span>() {
        <span class="hljs-keyword">return</span> uuid;
    }
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">setUuid</span>(String uuid) {
        <span class="hljs-keyword">this</span>.uuid = uuid;
    }
    <span class="hljs-keyword">public</span> String <span class="hljs-title">getName</span>() {
        <span class="hljs-keyword">return</span> name;
    }
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">setName</span>(String name) {
        <span class="hljs-keyword">this</span>.name = name;
    }
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">double</span> <span class="hljs-title">getInPrice</span>() {
        <span class="hljs-keyword">return</span> inPrice;
    }
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">setInPrice</span>(<span class="hljs-keyword">double</span> inPrice) {
        <span class="hljs-keyword">this</span>.inPrice = inPrice;
    }
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">double</span> <span class="hljs-title">getOutPrice</span>() {
        <span class="hljs-keyword">return</span> outPrice;
    }
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">setOutPrice</span>(<span class="hljs-keyword">double</span> outPrice) {
        <span class="hljs-keyword">this</span>.outPrice = outPrice;
    }
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">int</span> <span class="hljs-title">getNum</span>() {
        <span class="hljs-keyword">return</span> num;
    }
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">setNum</span>(<span class="hljs-keyword">int</span> num) {
        <span class="hljs-keyword">this</span>.num = num;
    }
    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">int</span> <span class="hljs-title">hashCode</span>() {
        <span class="hljs-keyword">final</span> <span class="hljs-keyword">int</span> prime = <span class="hljs-number">31</span>;
        <span class="hljs-keyword">int</span> result = <span class="hljs-number">1</span>;
        result = prime * result + ((uuid == <span class="hljs-keyword">null</span>) ? <span class="hljs-number">0</span> : uuid.hashCode());
        <span class="hljs-keyword">return</span> result;
    }
    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">boolean</span> <span class="hljs-title">equals</span>(Object obj) {
        <span class="hljs-keyword">if</span> (<span class="hljs-keyword">this</span> == obj)
            <span class="hljs-keyword">return</span> <span class="hljs-keyword">true</span>;
        <span class="hljs-keyword">if</span> (obj == <span class="hljs-keyword">null</span>)
            <span class="hljs-keyword">return</span> <span class="hljs-keyword">false</span>;
        <span class="hljs-keyword">if</span> (getClass() != obj.getClass())
            <span class="hljs-keyword">return</span> <span class="hljs-keyword">false</span>;
        Book other = (Book) obj;
        <span class="hljs-keyword">if</span> (uuid == <span class="hljs-keyword">null</span>) {
            <span class="hljs-keyword">if</span> (other.uuid != <span class="hljs-keyword">null</span>)
                <span class="hljs-keyword">return</span> <span class="hljs-keyword">false</span>;
        } <span class="hljs-keyword">else</span> <span class="hljs-keyword">if</span> (!uuid.equals(other.uuid))
            <span class="hljs-keyword">return</span> <span class="hljs-keyword">false</span>;
        <span class="hljs-keyword">return</span> <span class="hljs-keyword">true</span>;
    }
    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> String <span class="hljs-title">toString</span>() {
        <span class="hljs-keyword">return</span> <span class="hljs-string">"Book [uuid="</span> + uuid + <span class="hljs-string">", name="</span> + name + <span class="hljs-string">", inPrice="</span> + inPrice
                + <span class="hljs-string">", outPrice="</span> + outPrice + <span class="hljs-string">", num="</span> + num + <span class="hljs-string">"]"</span>;
    }
}
</code></pre>

<h1 id="过度版的">过度版的：</h1>

<p>先看过度版的:接参后需要强转成对应的bean，因为返回类型是Object。</p>



<h2 id="mybeanutils1">MyBeanUtils1</h2>



<pre class="prettyprint"><code class=" hljs cs">package cn.hncu.beanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

<span class="hljs-keyword">public</span> <span class="hljs-keyword">class</span> MyBeanUtils1 {

    <span class="hljs-keyword">public</span> <span class="hljs-keyword">static</span> Object <span class="hljs-title">populate</span>(Class cls ,Map map) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
        Object obj = <span class="hljs-keyword">null</span>;

        <span class="hljs-comment">//1、用类反射new出对象</span>
        obj = cls.newInstance();

        <span class="hljs-comment">//2 再用类反射对新new的对象设置属性值(必须遵守Java设置规范)--即通过setter方法设置</span>
        <span class="hljs-comment">//2.1遍历出所有该类声明的属性</span>
        Field flds[] = cls.getDeclaredFields();<span class="hljs-comment">//getDeclaredFields()返回Class中所有的字段，包括私有字段；</span>
        <span class="hljs-keyword">for</span>(Field fld:flds){
            <span class="hljs-comment">//获取该fld对象所代表的属性名</span>
            String fldName = fld.getName();
            <span class="hljs-comment">//根据属性名，到map中去读取数据，只有数据非空才需要给该属性设置值 </span>
            Object <span class="hljs-keyword">value</span> = map.<span class="hljs-keyword">get</span>(fldName);
            <span class="hljs-keyword">if</span>(<span class="hljs-keyword">value</span>==<span class="hljs-keyword">null</span>){<span class="hljs-comment">//如果map中不存在对应的属性数据，我们在这里给出提示信息</span>
                System.<span class="hljs-keyword">out</span>.println(fldName+<span class="hljs-string">"的数据为空"</span>);
            }<span class="hljs-keyword">else</span>{
                <span class="hljs-comment">//如果map中存在对应的属性数据，则由属性名得出它的setter方法的名字</span>
                String mothodName = <span class="hljs-string">"set"</span>+fldName.substring(<span class="hljs-number">0</span>, <span class="hljs-number">1</span>).toUpperCase()+fldName.substring(<span class="hljs-number">1</span>);

                 <span class="hljs-comment">//根据方法名和参数的数据类型(其实就是属性的类型)，获得Method对象</span>
                Class paramTypes[] = <span class="hljs-keyword">new</span> Class[<span class="hljs-number">1</span>];
                paramTypes[<span class="hljs-number">0</span>] = fld.getType();
                Method method = cls.getDeclaredMethod(mothodName, paramTypes);

                <span class="hljs-comment">//调用该method对象所代表的方法</span>
                Object args[] = <span class="hljs-keyword">new</span> Object[<span class="hljs-number">1</span>];
                args[<span class="hljs-number">0</span>]=<span class="hljs-keyword">value</span>;
                method.invoke(obj, args);
            }
        }
        <span class="hljs-keyword">return</span> obj;
    }

}</code></pre>



<h2 id="测试">测试</h2>



<pre class="prettyprint"><code class=" hljs avrasm">@Test
    public void test1() {
        Map map = new HashMap()<span class="hljs-comment">;</span>
        map<span class="hljs-preprocessor">.put</span>(<span class="hljs-string">"uuid"</span>, <span class="hljs-string">"001"</span>)<span class="hljs-comment">;</span>
        map<span class="hljs-preprocessor">.put</span>(<span class="hljs-string">"name"</span>, <span class="hljs-string">"Jack"</span>)<span class="hljs-comment">;</span>
        map<span class="hljs-preprocessor">.put</span>(<span class="hljs-string">"age"</span>, <span class="hljs-number">20</span>)<span class="hljs-comment">;</span>

        Map map2 = new HashMap()<span class="hljs-comment">;</span>
        map2<span class="hljs-preprocessor">.put</span>(<span class="hljs-string">"uuid"</span>, <span class="hljs-string">"001"</span>)<span class="hljs-comment">;</span>
        map2<span class="hljs-preprocessor">.put</span>(<span class="hljs-string">"name"</span>, <span class="hljs-string">"红楼梦"</span>)<span class="hljs-comment">;</span>
        map2<span class="hljs-preprocessor">.put</span>(<span class="hljs-string">"inPrice"</span>, <span class="hljs-number">20.5</span>)<span class="hljs-comment">;</span>
        //数据可能不全
        map2<span class="hljs-preprocessor">.put</span>(<span class="hljs-string">"num"</span>, <span class="hljs-number">123</span>)<span class="hljs-comment">;</span>
        try {
            User user =  (User) MyBeanUtils1<span class="hljs-preprocessor">.populate</span>(User<span class="hljs-preprocessor">.class</span>, map)<span class="hljs-comment">;</span>
            System<span class="hljs-preprocessor">.out</span><span class="hljs-preprocessor">.println</span>(user)<span class="hljs-comment">;</span>
            Book book =  (Book) MyBeanUtils1<span class="hljs-preprocessor">.populate</span>(Book<span class="hljs-preprocessor">.class</span>, map2)<span class="hljs-comment">;</span>
            System<span class="hljs-preprocessor">.out</span><span class="hljs-preprocessor">.println</span>(book)<span class="hljs-comment">;</span>
        } catch (ReflectiveOperationException e) {
            e<span class="hljs-preprocessor">.printStackTrace</span>()<span class="hljs-comment">;</span>
        }
    }</code></pre>



<h2 id="测试结果">测试结果:</h2>

<p><img src="http://img.blog.csdn.net/20160825145259043" alt="" title=""></p>

<p>这个还不是很完善，为什么呢，因为返回类型是Object，每次都要强转，比较麻烦，而且我们传了bean的class对象过去了，完全可以实现不用强转的，这个时候我们就需要用到泛型了。 <br>
而且Map的泛型我们可以确定了，肯定是<code>Map&lt;String,Object&gt;</code>这样的</p>

<p>好了，学习一下最终版的、</p>

<h1 id="最终版">最终版:</h1>



<h2 id="mybeanutils">MyBeanUtils</h2>



<pre class="prettyprint"><code class=" hljs cs">package cn.hncu.beanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

<span class="hljs-keyword">public</span> <span class="hljs-keyword">class</span> MyBeanUtils {

    <span class="hljs-keyword">public</span> <span class="hljs-keyword">static</span>&lt;T&gt; T <span class="hljs-title">populate</span>(Class&lt;T&gt; cls ,Map&lt;String, Object&gt; map) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
        T obj = <span class="hljs-keyword">null</span>;

        <span class="hljs-comment">//1、用类反射new出对象</span>
        obj = cls.newInstance();

        <span class="hljs-comment">//2 再用类反射对新new的对象设置属性值(必须遵守Java设置规范)--即通过setter方法设置</span>
        <span class="hljs-comment">//2.1遍历出所有该类声明的属性</span>
        Field flds[] = cls.getDeclaredFields();<span class="hljs-comment">//getDeclaredFields()返回Class中所有的字段，包括私有字段；</span>
        <span class="hljs-keyword">for</span>(Field fld:flds){
            <span class="hljs-comment">//获取该fld对象所代表的属性名</span>
            String fldName = fld.getName();
            <span class="hljs-comment">//根据属性名，到map中去读取数据，只有数据非空才需要给该属性设置值 </span>
            Object <span class="hljs-keyword">value</span> = map.<span class="hljs-keyword">get</span>(fldName);
            <span class="hljs-keyword">if</span>(<span class="hljs-keyword">value</span>==<span class="hljs-keyword">null</span>){<span class="hljs-comment">//如果map中不存在对应的属性数据，我们在这里给出提示信息</span>
                System.<span class="hljs-keyword">out</span>.println(fldName+<span class="hljs-string">"的数据为空"</span>);
            }<span class="hljs-keyword">else</span>{
                <span class="hljs-comment">//如果map中存在对应的属性数据，则由属性名得出它的setter方法的名字</span>
                String mothodName = <span class="hljs-string">"set"</span>+fldName.substring(<span class="hljs-number">0</span>, <span class="hljs-number">1</span>).toUpperCase()+fldName.substring(<span class="hljs-number">1</span>);

                 <span class="hljs-comment">//根据方法名和参数的数据类型(其实就是属性的类型)，获得Method对象</span>
                Class&lt;?&gt; paramTypes[] = <span class="hljs-keyword">new</span> Class[<span class="hljs-number">1</span>];
                paramTypes[<span class="hljs-number">0</span>] = fld.getType();
                Method method = cls.getDeclaredMethod(mothodName, paramTypes);

                <span class="hljs-comment">//调用该method对象所代表的方法</span>
                Object args[] = <span class="hljs-keyword">new</span> Object[<span class="hljs-number">1</span>];
                args[<span class="hljs-number">0</span>]=<span class="hljs-keyword">value</span>;
                method.invoke(obj, args);
            }
        }
        <span class="hljs-keyword">return</span> obj;
    }

}
</code></pre>



<h2 id="测试方法">测试方法:</h2>



<pre class="prettyprint"><code class=" hljs avrasm">@Test
    @SuppressWarnings(<span class="hljs-string">"unchecked"</span>)
    public void test() {
        Map&lt;String, Object&gt; map = new HashMap()<span class="hljs-comment">;</span>
        map<span class="hljs-preprocessor">.put</span>(<span class="hljs-string">"uuid"</span>, <span class="hljs-string">"001"</span>)<span class="hljs-comment">;</span>
        map<span class="hljs-preprocessor">.put</span>(<span class="hljs-string">"name"</span>, <span class="hljs-string">"Jack"</span>)<span class="hljs-comment">;</span>
        map<span class="hljs-preprocessor">.put</span>(<span class="hljs-string">"age"</span>, <span class="hljs-number">20</span>)<span class="hljs-comment">;</span>

        Map&lt;String, Object&gt; map2 = new HashMap()<span class="hljs-comment">;</span>
        map2<span class="hljs-preprocessor">.put</span>(<span class="hljs-string">"uuid"</span>, <span class="hljs-string">"001"</span>)<span class="hljs-comment">;</span>
        map2<span class="hljs-preprocessor">.put</span>(<span class="hljs-string">"name"</span>, <span class="hljs-string">"红楼梦"</span>)<span class="hljs-comment">;</span>
        map2<span class="hljs-preprocessor">.put</span>(<span class="hljs-string">"inPrice"</span>, <span class="hljs-number">20.5</span>)<span class="hljs-comment">;</span>
        //数据可能不全
        map2<span class="hljs-preprocessor">.put</span>(<span class="hljs-string">"num"</span>, <span class="hljs-number">123</span>)<span class="hljs-comment">;</span>
        try {
            User user = MyBeanUtils<span class="hljs-preprocessor">.populate</span>(User<span class="hljs-preprocessor">.class</span>, map)<span class="hljs-comment">;</span>
            System<span class="hljs-preprocessor">.out</span><span class="hljs-preprocessor">.println</span>(user)<span class="hljs-comment">;</span>
            Book book = MyBeanUtils<span class="hljs-preprocessor">.populate</span>(Book<span class="hljs-preprocessor">.class</span>, map2)<span class="hljs-comment">;</span>
            System<span class="hljs-preprocessor">.out</span><span class="hljs-preprocessor">.println</span>(book)<span class="hljs-comment">;</span>
        } catch (ReflectiveOperationException e) {
            e<span class="hljs-preprocessor">.printStackTrace</span>()<span class="hljs-comment">;</span>
        }
    }</code></pre>



<h2 id="测试结果-1">测试结果:</h2>

<p><img src="http://img.blog.csdn.net/20160825150202112" alt="" title=""></p>

<blockquote cite="陈浩翔">
<p>转载请注明出处：<a href="http://blog.csdn.net/qq_26525215"><font color="green">http://blog.csdn.net/qq_26525215</font></a><br><br>
本文源自<strong>【<a href="http://blog.csdn.net/qq_26525215" target="_blank">大学之旅_谙忆的博客</a>】</strong></p>
</blockquote></div></body>
</html>