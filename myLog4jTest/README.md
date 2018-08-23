<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Log4j基本使用详解与应用于Java的实例</title>
<link rel="stylesheet" href="https://stackedit.io/res-min/themes/base.css" />
<script type="text/javascript" src="https://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS_HTML"></script>
</head>
<body><div class="container"><blockquote cite="陈浩翔">
<p>转载请注明出处：<a href="http://blog.csdn.net/qq_26525215"><font color="green">http://blog.csdn.net/qq_26525215</font></a><br><br>
本文源自<strong>【<a href="http://blog.csdn.net/qq_26525215" target="_blank">大学之旅_谙忆的博客</a>】</strong></p>
</blockquote>



<h1 id="log4j介绍">log4j介绍</h1>

<p>Log4j,具体的我就不去拷贝官网的介绍了，大家只要知道这是帮助我们记录日志的就可以了。</p>

<p>作用就是这2个： <br>
1、跟踪代码的运行轨迹。 <br>
2、输出调试信息</p>

<p>很简单，但是真的很实用的一个工具！不需要很深入的去了解，但是必须知道如何使用，如何通过Log4j去记录日志，这是必须的！</p>

<p>在这里，通过一个实例来演示如何使用log4j，以及如何配置log4j的配置文件。</p>



<h1 id="log4j-jar包">log4j-jar包:</h1>

<p>首先，我们需要准备一个Log4j的jar包，不多说，直接上链接: <br>
版本为:log4j-1.2.17.(2016.8.22下载的最先版本)’</p>

<blockquote>
<a href="https://github.com/chenhaoxiang/Java/tree/master/log4j_jar" target="_blank">点我去下载log4j-1.2.17-jar包</a>
</blockquote>

<p>三大组成就是如下三个类： <br>
1、Logger类-生成日志。 <br>
2、Appender类-定义日志输出的目的地。 <br>
3、Layout类-指定日志输出的格式。</p>



<h1 id="了解一下logger组件">了解一下Logger组件：</h1>

<p>日志共分为5种级别(ON开,OFF关这两个不计算在内)，从高到底（从严重到轻微）排序为:</p>

<p>FATAL—严重的错误,可能会导致程序不能正常运行. 类似System.exit()，整个项目都不能运行 <br>
  ERROR – 错误，影响程序的正常运行。           类似当前线程挂了，但其它线程或组件还在跑 <br>
  WARN – 警告，不会影响程序的运行。最好将此问题修正。 <br>
  INFO – 一般信息。不影响程序的运行。可能是用户的调试信息。可有可无。等同于system.err. <br>
  DEBUG – 一般的信息。等同于system.out</p>

<p>上面五种级别分别对应以下几个方法(Java中调用，等会会演示) <br>
  fatal(Object) <br>
  error(Object) <br>
  warn(Object) <br>
  info(Object) <br>
  debug(Object) <br>
当信息大于或是等于配置的级别时才会输出日志。</p>



<h1 id="appender组件">Appender组件</h1>

<p>我们只要记住下面这2个就可以了：</p>

<p>Console—控制台–org.apache.log4j.ConsoleAppender <br>
File—文件–org.apache.log4j.FileAppender , org.apache.log4j.RollingFileAppender</p>



<h1 id="layout组件">Layout组件</h1>

<p>功能是定义日志的输出格式 <br>
  org.apache.log4j.HTMLLayout <br>
  <strong>org.apache.log4j.PatternLayout</strong> (自定义) <br>
  org.apache.log4j.SimpleLayout</p>

<p>就是输出到日志的时候，用哪种格式输出，理解成正则表达式就好了。</p>

<p>有一些转义是他规定好的，我这里列出一些，不用全部都记下来，了解就行了，然后记一个常用的匹配格式就OK！</p>

<p>只解释自定义格式PatternLayout:</p>



<pre class="prettyprint"><code class=" hljs haml"><span class="hljs-tag">%<span class="hljs-title">d</span></span> – 具体的时间. %d{yyyy MMM dd HH:mm:ss,SSS}
        输出类似：输出类似：2016年8月21日 00:00:00,921
<span class="hljs-tag">%<span class="hljs-title">p</span></span> – 输出优先级，即DEBUG，INFO，WARN，ERROR，FATAL 
<span class="hljs-tag">%<span class="hljs-title">c</span></span> – 输出所属的类目，通常就是所在类的全名 -Logger.getLogger(name) 其实就是输出这个name，所以，我们这里一般用当前类的class。
<span class="hljs-tag">%<span class="hljs-title">L</span>(这是大写的L)</span> — 只输出行号
<span class="hljs-tag">%<span class="hljs-title">l</span></span> - 输出日志事件的发生位置，包括类目名、发生的线程，以及在代码中的行数。举例：(Log4jDemo.java:13)
<span class="hljs-tag">%<span class="hljs-title">m</span></span> – 输出代码中指定的消息-我们指定的
<span class="hljs-tag">%<span class="hljs-title">n</span></span> —换行符-Windows平台为“\r\n”，Unix平台为“\n” 
<span class="hljs-tag">%<span class="hljs-title">r</span></span>   输出自应用启动到输出该log信息耗费的毫秒数 
<span class="hljs-tag">%<span class="hljs-title">t</span></span>   输出产生该日志事件的线程名</code></pre>



<h1 id="配置文件的一个模板">配置文件的一个模板:</h1>

<p>在src/bin目录下（即WEB-INF/classes）建立一个log4j.properties文件.</p>

<p>在WEB-INF/classes目录下，为log4j的默认目录。Log4j.properties也是log4j的默认文件名。</p>

<pre class="prettyprint"><code class=" hljs avrasm"><span class="hljs-preprocessor">#####配置根元素</span>
log4j<span class="hljs-preprocessor">.rootLogger</span>=INFO,console,file
<span class="hljs-preprocessor">#存储INFO以及以上级别的记录</span>
<span class="hljs-preprocessor">#file的输入地址为文件</span>
<span class="hljs-preprocessor">#console表示输出到控制台</span>
log4j<span class="hljs-preprocessor">.appender</span><span class="hljs-preprocessor">.file</span>=org<span class="hljs-preprocessor">.apache</span><span class="hljs-preprocessor">.log</span>4j<span class="hljs-preprocessor">.RollingFileAppender</span>
<span class="hljs-preprocessor">#以下是具体的目录</span>
log4j<span class="hljs-preprocessor">.appender</span><span class="hljs-preprocessor">.file</span><span class="hljs-preprocessor">.File</span>=d:/a/test<span class="hljs-preprocessor">.log</span>
<span class="hljs-preprocessor">#每个文件的最大大小</span>
log4j<span class="hljs-preprocessor">.appender</span><span class="hljs-preprocessor">.file</span><span class="hljs-preprocessor">.MaxFileSize</span>=<span class="hljs-number">20</span>KB
<span class="hljs-preprocessor">#最多可以是多少个文件</span>
log4j<span class="hljs-preprocessor">.appender</span><span class="hljs-preprocessor">.file</span><span class="hljs-preprocessor">.MaxBackupIndex</span>=<span class="hljs-number">3</span>
<span class="hljs-preprocessor">#信息的布局格式-按指定的格式打出</span>
log4j<span class="hljs-preprocessor">.appender</span><span class="hljs-preprocessor">.file</span><span class="hljs-preprocessor">.layout</span>=org<span class="hljs-preprocessor">.apache</span><span class="hljs-preprocessor">.log</span>4j<span class="hljs-preprocessor">.PatternLayout</span>
<span class="hljs-preprocessor">#具体的布局格式 - %d为时间</span>
<span class="hljs-preprocessor">#log4j.appender.file.layout.ConversionPattern=%d %p [%c] %m %l %n</span>
log4j<span class="hljs-preprocessor">.appender</span><span class="hljs-preprocessor">.file</span><span class="hljs-preprocessor">.layout</span><span class="hljs-preprocessor">.ConversionPattern</span>=%d %p [%l]\:%m -%t%n 

<span class="hljs-preprocessor">#以下配置输出到控制台的配置</span>
log4j<span class="hljs-preprocessor">.appender</span><span class="hljs-preprocessor">.console</span>=org<span class="hljs-preprocessor">.apache</span><span class="hljs-preprocessor">.log</span>4j<span class="hljs-preprocessor">.ConsoleAppender</span>
log4j<span class="hljs-preprocessor">.appender</span><span class="hljs-preprocessor">.console</span><span class="hljs-preprocessor">.layout</span>=org<span class="hljs-preprocessor">.apache</span><span class="hljs-preprocessor">.log</span>4j<span class="hljs-preprocessor">.PatternLayout</span>
<span class="hljs-preprocessor">#log4j.appender.console.layout.ConversionPattern=%d %p [%c] %m%n</span>
log4j<span class="hljs-preprocessor">.appender</span><span class="hljs-preprocessor">.console</span><span class="hljs-preprocessor">.layout</span><span class="hljs-preprocessor">.ConversionPattern</span>=%d %p [%l]\:%m -%t%n </code></pre>



<h1 id="演示实例">演示实例:</h1>



<pre class="prettyprint"><code class=" hljs java"><span class="hljs-keyword">package</span> cn.hncu.lib;

<span class="hljs-keyword">import</span> org.apache.log4j.Logger;
<span class="hljs-javadoc">/**
 *<span class="hljs-javadoctag"> @author</span> 陈浩翔
 *
 * 2016-8-23
 */</span>
<span class="hljs-keyword">public</span> <span class="hljs-class"><span class="hljs-keyword">class</span> <span class="hljs-title">Log4jDemo</span> {</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">static</span> <span class="hljs-keyword">void</span> <span class="hljs-title">main</span>(String[] args) {
        <span class="hljs-comment">//日志的名字是传入的任何字符串，通常为类名或包名</span>
        Logger log = Logger.getLogger(Log4jDemo.class);

        <span class="hljs-keyword">for</span>(<span class="hljs-keyword">int</span> i=<span class="hljs-number">0</span>;i&lt;<span class="hljs-number">10</span>;i++){
            log.fatal(<span class="hljs-string">"严重错误信息:"</span>+i);
            log.error(<span class="hljs-string">"错误信息:"</span>+i);
            log.warn(<span class="hljs-string">"警告信息:"</span>+i);
            log.info(<span class="hljs-string">"一般信息:"</span>+i);
            log.debug(<span class="hljs-string">"调试信息:"</span>+i);
        }
    }
}
</code></pre>

<h1 id="演示结果">演示结果:</h1>



<h2 id="控制台输出">控制台输出:</h2>

<p><img src="http://img.blog.csdn.net/20160823112923649" alt="" title=""></p>



<h2 id="文件输出">文件输出:</h2>

<p><img src="http://img.blog.csdn.net/20160823113005761" alt="" title=""></p>

<p>是不是很简单，配置好文件之后，我们在Java中用，只需要2步。 <br>
1、Logger log = Logger.getLogger(<code>****</code>.class); <br>
2、随意调用下面5个方法:</p>



<pre class="prettyprint"><code class=" hljs perl"><span class="hljs-keyword">log</span>.fatal();
<span class="hljs-keyword">log</span>.error();
<span class="hljs-keyword">log</span>.<span class="hljs-keyword">warn</span>();
<span class="hljs-keyword">log</span>.info();
<span class="hljs-keyword">log</span>.debug();
参数是Object类型！在日志中用<span class="hljs-variable">%m</span>来代表我们这里传的值的toString()。</code></pre>

<p>如果要去更加详细了解的，可以去Log4j官网:</p>

<blockquote cite="陈浩翔">
<p><a href="https://logging.apache.org/log4j/1.2/"><font color="blue">Log4j官网</font></a></p>
</blockquote>

<blockquote cite="陈浩翔">
<p>转载请注明出处：<a href="http://blog.csdn.net/qq_26525215"><font color="green">http://blog.csdn.net/qq_26525215</font></a><br><br>
本文源自<strong>【<a href="http://blog.csdn.net/qq_26525215" target="_blank">大学之旅_谙忆的博客</a>】</strong></p>
</blockquote></div></body>
</html>