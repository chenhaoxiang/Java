<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>【框架】[Spring] 基于Spring框架的Web应用演示(附带cglib工具进行动态代理)</title>
<link rel="stylesheet" href="https://stackedit.io/res-min/themes/base.css" />
<script type="text/javascript" src="https://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS_HTML"></script>
</head>
<body><div class="container"><blockquote cite="陈浩翔">
<p>转载请注明出处：<a href="http://blog.csdn.net/qq_26525215"><font color="green">http://blog.csdn.net/qq_26525215</font></a><br><br>
本文源自<strong>【<a href="http://blog.csdn.net/qq_26525215" target="_blank">大学之旅_谙忆的博客</a>】</strong></p>
</blockquote>



<h1 id="前言">前言：</h1>

<p>Spring也差不多学了Ioc控制反转和实现AOP技术的两种方式了，分享一个学习Spring，用来入门挺好的例子。</p>

<p>如果你是刚刚学习Spring，那么此实例应该可以很好的帮助你应用Spring到Web项目中。</p>

<p>里面的DAO层-提交数据库的事务我并没有使用Spring 的注解功能，而是用spring的AOP来实现的。这样更灵活，其实，框架为我们做的事越多，我们就越受框架的约束。想把功能做灵活，就越难实现。</p>

<p>只要我们把底层学好，框架的功能我们都能自己写出来的，而且自己写出来的东西，肯定会更熟悉。 <br>
<strong>框架是为了降低程序之间的依赖性和耦合性，使重用性达到最高。</strong></p>

<p>学习框架，<strong>我更多的希望自己能学会框架的思想</strong>，理解为什么！</p>



<h1 id="首先准备数据库">首先准备数据库:</h1>



<pre class="prettyprint"><code class=" hljs sql"><span class="hljs-operator"><span class="hljs-keyword">create</span> <span class="hljs-keyword">database</span> mydb charset=utf8;</span>

<span class="hljs-operator"><span class="hljs-keyword">create</span> <span class="hljs-keyword">table</span> stud(
  s_id <span class="hljs-keyword">varchar</span>(<span class="hljs-number">32</span>) <span class="hljs-keyword">primary</span> <span class="hljs-keyword">key</span>,
  s_name <span class="hljs-keyword">varchar</span>(<span class="hljs-number">40</span>)
);</span>

<span class="hljs-operator"><span class="hljs-keyword">create</span> <span class="hljs-keyword">table</span> book(
  b_id <span class="hljs-keyword">int</span> <span class="hljs-keyword">primary</span> <span class="hljs-keyword">key</span>  auto_increment,
  b_name <span class="hljs-keyword">varchar</span>(<span class="hljs-number">40</span>)
);</span></code></pre>

<p>准备好这2个表：</p>

<p><img src="http://img.blog.csdn.net/20160904101443352" alt="" title=""></p>



<h1 id="jar包少不了">Jar包少不了:</h1>

<p><img src="http://img.blog.csdn.net/20160904104418017" alt="" title=""></p>

<p>相信学到这一步的朋友应该有了自己的一个配套包了吧，在这里我就不去一 一将包链接写出了。 <br>
如果需要这些包的，在本博客最后我会给出整个项目的链接，请到里面的WEB-INF/lib目录下去下载。</p>



<h1 id="配好webxml">配好web.xml:</h1>

<p>配置web.xml-以使用Spring。</p>



<pre class="prettyprint"><code class=" hljs xml">  <span class="hljs-tag">&lt;<span class="hljs-title">context-param</span>&gt;</span>
        <span class="hljs-comment">&lt;!-- param的name必须为contextConfigLocation,Spring内部会解析的 --&gt;</span>
        <span class="hljs-tag">&lt;<span class="hljs-title">param-name</span>&gt;</span>contextConfigLocation<span class="hljs-tag">&lt;/<span class="hljs-title">param-name</span>&gt;</span>
        <span class="hljs-comment">&lt;!-- contextConfigLocation参数的值，课配置多个，用英文逗号隔开 --&gt;</span>
        <span class="hljs-tag">&lt;<span class="hljs-title">param-value</span>&gt;</span>
            classpath:beans.xml,
            /WEB-INF/conf/applicationContext.xml
        <span class="hljs-tag">&lt;/<span class="hljs-title">param-value</span>&gt;</span>
  <span class="hljs-tag">&lt;/<span class="hljs-title">context-param</span>&gt;</span>
  <span class="hljs-tag">&lt;<span class="hljs-title">listener</span>&gt;</span>
        <span class="hljs-tag">&lt;<span class="hljs-title">listener-class</span>&gt;</span>org.springframework.web.context.ContextLoaderListener<span class="hljs-tag">&lt;/<span class="hljs-title">listener-class</span>&gt;</span>
  <span class="hljs-tag">&lt;/<span class="hljs-title">listener</span>&gt;</span></code></pre>

<p>org.springframework.web.context.ContextLoaderListener监听器的作用就是启动Web容器时，自动装配ApplicationContext的配置信息。因为它实现了ServletContextListener这个接口，在web.xml配置这个监听器，启动容器时，就会默认执行Spring实现的方法。</p>

<p>小知识点: <br>
容器对于web.xml的加载过程是context-param &gt;&gt; listener  &gt;&gt; fileter  &gt;&gt; servlet.</p>

<p>接下来就是写： <br>
classpath:beans.xml和/WEB-INF/conf/applicationContext.xml这2个xml。 <br>
classpath:代表beans.xml的位置在src(bin)目录下。</p>

<p>既然需要连接数据库，我们还需要一个配置文件jdbc.properties，声明一些数据库的协议(其实可以在applicationContext.xml中直接配置的，可以不用这个文件)</p>



<h1 id="jdbcproperties">jdbc.properties:</h1>

<p><img src="http://img.blog.csdn.net/20160904105200885" alt="" title=""></p>



<pre class="prettyprint"><code class=" hljs vala"><span class="hljs-preprocessor">#如果是utf-8编码，第一行必须空一行.因为utf-8的文件开头有一个符号</span>
<span class="hljs-preprocessor">#在本例中，我用这个配置文件会出现账号密码错误，无法连接数据库，原因未知</span>
<span class="hljs-preprocessor">#所以，我在本例是直接配置dataSource的，未用到本文件</span>
<span class="hljs-preprocessor">#数据库驱动包</span>
driver=com.mysql.jdbc.Driver
<span class="hljs-preprocessor">#连接数据库的协议--三个"/"代表通过数据库默认端口连接本机的数据库，也可以写成//localhost:3306/</span>
url=jdbc:mysql:<span class="hljs-comment">///mydb?characterEncoding=utf-8</span>
username=root
pwd=<span class="hljs-number">1234</span>
<span class="hljs-preprocessor">#其实前面的4个变量名都是自己随便可以取的，因为真正的读取不是在这里</span>
<span class="hljs-preprocessor">#真正的读取在applicationContext.xml中</span></code></pre>

<p>在applicationContext.xml配置如下就可以拿到数据库连接了。</p>



<pre class="prettyprint"><code class=" hljs xml">    <span class="hljs-comment">&lt;!-- 使用jdbc.properties配置文件,就要写下面这句 --&gt;</span>
<span class="hljs-comment">&lt;!--    &lt;context:property-placeholder location="WEB-INF/conf/jdbc.properties"/&gt; --&gt;</span>
    <span class="hljs-tag">&lt;<span class="hljs-title">bean</span> <span class="hljs-attribute">id</span>=<span class="hljs-value">"dataSource"</span> <span class="hljs-attribute">class</span>=<span class="hljs-value">"org.springframework.jdbc.datasource.SimpleDriverDataSource"</span>&gt;</span>
        <span class="hljs-comment">&lt;!-- 使用jdbc.properties配置文件,类似如下配置 --&gt;</span>
<span class="hljs-comment">&lt;!--        &lt;property name="driverClass" value="${driver}"&gt;&lt;/property&gt; --&gt;</span>
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"driverClass"</span> <span class="hljs-attribute">value</span>=<span class="hljs-value">"com.mysql.jdbc.Driver"</span>&gt;</span><span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span> 
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"url"</span> <span class="hljs-attribute">value</span>=<span class="hljs-value">"jdbc:mysql:///mydb?characterEncoding=UTF-8"</span>&gt;</span><span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span> 
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"username"</span> <span class="hljs-attribute">value</span>=<span class="hljs-value">"root"</span>&gt;</span><span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span> 
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"password"</span> <span class="hljs-attribute">value</span>=<span class="hljs-value">"1234"</span>&gt;</span><span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span> 
    <span class="hljs-tag">&lt;/<span class="hljs-title">bean</span>&gt;</span></code></pre>



<h1 id="beansxml">beans.xml</h1>

<p>写好DAO,service,servlet层的架构-方法和变量: <br>
在beans.xml中配置好DAO,service的初始化bean,初始化属性。 <br>
而由于我们在web.xml配置了servlet，是Tomcat帮我们new-servlet的，所以，但是我们需要在servlet中需要访问service的对象,这个时候，我们就可以利用servlet的生命周期，在init方法中，给service对象赋值.</p>



<pre class="prettyprint"><code class=" hljs xml"><span class="hljs-pi">&lt;?xml version="1.0" encoding="UTF-8"?&gt;</span>
<span class="hljs-tag">&lt;<span class="hljs-title">beans</span> <span class="hljs-attribute">xmlns</span>=<span class="hljs-value">"http://www.springframework.org/schema/beans"</span>
        <span class="hljs-attribute">xmlns:xsi</span>=<span class="hljs-value">"http://www.w3.org/2001/XMLSchema-instance"</span>
        <span class="hljs-attribute">xmlns:context</span>=<span class="hljs-value">"http://www.springframework.org/schema/context"</span>
        <span class="hljs-attribute">xmlns:tx</span>=<span class="hljs-value">"http://www.springframework.org/schema/tx"</span>
        <span class="hljs-attribute">xsi:schemaLocation</span>=<span class="hljs-value">"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.3.xsd
                http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd
                http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.3.xsd"</span>&gt;</span>
    <span class="hljs-tag">&lt;<span class="hljs-title">bean</span> <span class="hljs-attribute">id</span>=<span class="hljs-value">"studDao"</span> <span class="hljs-attribute">class</span>=<span class="hljs-value">"cn.hncu.stud.dao.StudDaoJdbc"</span>&gt;</span>
       <span class="hljs-comment">&lt;!--ref="dataSource",引用applicationContext.xml中的dataSource  --&gt;</span>
       <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"dataSource"</span> <span class="hljs-attribute">ref</span>=<span class="hljs-value">"dataSource"</span>&gt;</span><span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>
    <span class="hljs-tag">&lt;/<span class="hljs-title">bean</span>&gt;</span>
    <span class="hljs-tag">&lt;<span class="hljs-title">bean</span> <span class="hljs-attribute">id</span>=<span class="hljs-value">"bookDao"</span> <span class="hljs-attribute">class</span>=<span class="hljs-value">"cn.hncu.stud.dao.BookDaoJdbc"</span>&gt;</span>
       <span class="hljs-comment">&lt;!--ref="dataSource",引用applicationContext.xml中的dataSource  --&gt;</span>
       <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"dataSource"</span> <span class="hljs-attribute">ref</span>=<span class="hljs-value">"dataSource"</span>&gt;</span><span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>
    <span class="hljs-tag">&lt;/<span class="hljs-title">bean</span>&gt;</span>

    <span class="hljs-tag">&lt;<span class="hljs-title">bean</span> <span class="hljs-attribute">id</span>=<span class="hljs-value">"saveService"</span> <span class="hljs-attribute">class</span>=<span class="hljs-value">"cn.hncu.stud.service.StudServiceImpl"</span>&gt;</span>
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"studDao"</span> <span class="hljs-attribute">ref</span>=<span class="hljs-value">"studDao"</span>&gt;</span><span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"bookDao"</span> <span class="hljs-attribute">ref</span>=<span class="hljs-value">"bookDao"</span>&gt;</span><span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>
    <span class="hljs-tag">&lt;/<span class="hljs-title">bean</span>&gt;</span>
<span class="hljs-tag">&lt;/<span class="hljs-title">beans</span>&gt;</span>
</code></pre>



<h2 id="servlet中加入此方法实现service的初始化">servlet中加入此方法，实现service的初始化:</h2>



<pre class="prettyprint"><code class=" hljs java"><span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">init</span>() <span class="hljs-keyword">throws</span> ServletException {
        <span class="hljs-comment">//在这里，我们可以直接获取Web中的Spring容器-不能重新去new，因为那样就不是同一个容器的了</span>
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        service=ctx.getBean(ISaveService.class);
    }</code></pre>



<h1 id="txadvice-aop通知">TxAdvice-AOP通知</h1>



<pre class="prettyprint"><code class=" hljs java"><span class="hljs-keyword">package</span> cn.hncu.utils;

<span class="hljs-keyword">import</span> java.sql.Connection;

<span class="hljs-keyword">import</span> javax.sql.DataSource;

<span class="hljs-keyword">import</span> org.aopalliance.intercept.MethodInterceptor;
<span class="hljs-keyword">import</span> org.aopalliance.intercept.MethodInvocation;
<span class="hljs-keyword">import</span> org.springframework.beans.BeansException;
<span class="hljs-keyword">import</span> org.springframework.context.ApplicationContext;
<span class="hljs-keyword">import</span> org.springframework.context.ApplicationContextAware;

<span class="hljs-comment">//另外一种方法获取Web中的spring容器--实现ApplicationContextAware接口</span>
<span class="hljs-keyword">public</span> <span class="hljs-class"><span class="hljs-keyword">class</span> <span class="hljs-title">TxAdvice</span> <span class="hljs-keyword">implements</span> <span class="hljs-title">MethodInterceptor</span>,<span class="hljs-title">ApplicationContextAware</span>{</span>
    <span class="hljs-keyword">private</span> ApplicationContext ctx =<span class="hljs-keyword">null</span>;

    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">setApplicationContext</span>(ApplicationContext ctx)
            <span class="hljs-keyword">throws</span> BeansException {
        <span class="hljs-keyword">this</span>.ctx=ctx;
    }

    <span class="hljs-comment">//通知---这个里面需要拿到dataSource，所以需要先拿到Spring的容器</span>
    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> Object <span class="hljs-title">invoke</span>(MethodInvocation invocation) <span class="hljs-keyword">throws</span> Throwable {
        DataSource dataSource = ctx.getBean(DataSource.class);

        Connection con = dataSource.getConnection();
        Object res =<span class="hljs-keyword">null</span>;
        <span class="hljs-keyword">try</span> {
            con.setAutoCommit(<span class="hljs-keyword">false</span>);
            <span class="hljs-comment">//开启一个事务</span>
            res = invocation.proceed();<span class="hljs-comment">//放行</span>
            con.commit();<span class="hljs-comment">//提交</span>
            System.out.println(<span class="hljs-string">"提交一个事务..."</span>);
        } <span class="hljs-keyword">catch</span> (Exception e) {
            con.rollback();
            System.out.println(<span class="hljs-string">"事务回滚..."</span>);
        }<span class="hljs-keyword">finally</span>{
            con.setAutoCommit(<span class="hljs-keyword">true</span>);<span class="hljs-comment">//关闭事务</span>
            con.close();
        }
        <span class="hljs-keyword">return</span> res;
    }


}
</code></pre>



<h2 id="事务">事务</h2>

<p>如果只代理到上面这里，写con.close方法其实会出问题的。 <br>
当然，本例很简单，servlet只请求了一个service中的一个方法，这样写没什么问题， <br>
但是，假如我有多个service和一个service有多个方法，需要被一个用户请求servlet时同时调用时，这个连接就不能被关闭了。 <br>
因为Spring容器的事务机制的实质是对传统JDBC的封装，也即是Spring事务管理无论是对单数据库实例还是分布式数据库实例，要实现事务管理，那么必须保证在一个事务过程获得Connetion对象是同一个。</p>

<p>假如是servlet调用多个service或service中多个方法，需要实现的是同一个事务，我们可以：在service中写一个综合方法，在其中调用其它方法，然后给综合方法设置代理，因为这个综合方法在这里就是一个业务 <br>
，多个service，原理一样。</p>



<h1 id="aop拦截getconnection方法cglib工具进行动态代理connection">AOP拦截getConnection()方法，cglib工具进行动态代理Connection</h1>

<p>然后再拦截Connection的close方法！</p>



<pre class="prettyprint"><code class=" hljs java"><span class="hljs-keyword">package</span> cn.hncu.utils;

<span class="hljs-keyword">import</span> java.lang.reflect.Method;
<span class="hljs-keyword">import</span> java.sql.Connection;

<span class="hljs-keyword">import</span> net.sf.cglib.proxy.Callback;
<span class="hljs-keyword">import</span> net.sf.cglib.proxy.Enhancer;
<span class="hljs-keyword">import</span> net.sf.cglib.proxy.MethodProxy;

<span class="hljs-keyword">import</span> org.aopalliance.intercept.MethodInterceptor;
<span class="hljs-keyword">import</span> org.aopalliance.intercept.MethodInvocation;

<span class="hljs-keyword">public</span> <span class="hljs-class"><span class="hljs-keyword">class</span> <span class="hljs-title">CloseAdvice</span> <span class="hljs-keyword">implements</span> <span class="hljs-title">MethodInterceptor</span>{</span>
    <span class="hljs-keyword">private</span> ThreadLocal&lt;Object&gt; t = <span class="hljs-keyword">new</span> ThreadLocal&lt;Object&gt;();

    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> Object <span class="hljs-title">invoke</span>(MethodInvocation invocation) <span class="hljs-keyword">throws</span> Throwable {

        Object obj = t.get();
        <span class="hljs-keyword">if</span>(obj==<span class="hljs-keyword">null</span>){

            <span class="hljs-keyword">final</span> Object con = invocation.proceed();<span class="hljs-comment">//返回原型对象Connection</span>

            <span class="hljs-comment">//通过cglib工具进行动态代理</span>
            Callback callback = <span class="hljs-keyword">new</span> net.sf.cglib.proxy.MethodInterceptor() {

                <span class="hljs-annotation">@Override</span>
                <span class="hljs-keyword">public</span> Object <span class="hljs-title">intercept</span>(Object proxiedObj, Method method,
                        Object[] args, MethodProxy proxy) <span class="hljs-keyword">throws</span> Throwable {
                    <span class="hljs-keyword">if</span>(method.getName().equals(<span class="hljs-string">"close"</span>)){
                        <span class="hljs-keyword">return</span> <span class="hljs-keyword">null</span>;
                    }
                    <span class="hljs-comment">//con为原型Connection对象</span>
                    <span class="hljs-keyword">return</span> method.invoke(con, args);
                }
            };

            <span class="hljs-comment">//obj为cglib工具代理后的Connection对象</span>
            obj=Enhancer.create(Connection.class, callback);
            t.set(obj);
        }
        <span class="hljs-keyword">return</span> obj;
    }





}
</code></pre>



<h2 id="在applicationcontextxml中配置拦截getconnection">在applicationContext.xml中配置拦截getConnection()</h2>



<pre class="prettyprint"><code class=" hljs applescript">&lt;bean <span class="hljs-property">id</span>=<span class="hljs-string">"conClose"</span> <span class="hljs-type">class</span>=<span class="hljs-string">"org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor"</span>&gt;
       &lt;<span class="hljs-keyword">property</span> <span class="hljs-property">name</span>=<span class="hljs-string">"expression"</span> value=<span class="hljs-string">"execution( * *..*.*.getConnection() )"</span>&gt;&lt;/<span class="hljs-keyword">property</span>&gt;
       &lt;<span class="hljs-keyword">property</span> <span class="hljs-property">name</span>=<span class="hljs-string">"advice"</span>&gt;
            &lt;bean <span class="hljs-property">id</span>=<span class="hljs-string">"advice"</span> <span class="hljs-type">class</span>=<span class="hljs-string">"cn.hncu.utils.CloseAdvice"</span>&gt;&lt;/bean&gt;
       &lt;/<span class="hljs-keyword">property</span>&gt;
    &lt;/bean&gt;</code></pre>

<p>接下来就是要用到AOP了，拦截事务。 <br>
拦截service层的。</p>



<h1 id="拦截事务的切面配置">拦截事务的切面配置:</h1>



<pre class="prettyprint"><code class=" hljs xml"><span class="hljs-comment">&lt;!-- 自动代理 --&gt;</span>
    <span class="hljs-tag">&lt;<span class="hljs-title">bean</span> <span class="hljs-attribute">class</span>=<span class="hljs-value">"org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator"</span>&gt;</span><span class="hljs-tag">&lt;/<span class="hljs-title">bean</span>&gt;</span>
    <span class="hljs-comment">&lt;!-- 事务  切面=切点+通知 --&gt;</span>
    <span class="hljs-tag">&lt;<span class="hljs-title">bean</span> <span class="hljs-attribute">id</span>=<span class="hljs-value">"tx"</span> <span class="hljs-attribute">class</span>=<span class="hljs-value">"org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor"</span>&gt;</span>
        <span class="hljs-comment">&lt;!-- 拦截cn.hncu包下的，方法名最后为Service的任意返回值任意参数的方法 --&gt;</span>
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"expression"</span> <span class="hljs-attribute">value</span>=<span class="hljs-value">"execution (* cn.hncu..*Service.*(..) )"</span>&gt;</span>
        <span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"advice"</span>&gt;</span>
            <span class="hljs-tag">&lt;<span class="hljs-title">bean</span> <span class="hljs-attribute">class</span>=<span class="hljs-value">"cn.hncu.utils.TxAdvice"</span>&gt;</span><span class="hljs-tag">&lt;/<span class="hljs-title">bean</span>&gt;</span>
        <span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>
    <span class="hljs-tag">&lt;/<span class="hljs-title">bean</span>&gt;</span></code></pre>



<h1 id="dao层的实现类代码">DAO层的实现类代码:</h1>



<h2 id="stud的实现类">stud的实现类:</h2>



<pre class="prettyprint"><code class=" hljs java"><span class="hljs-keyword">package</span> cn.hncu.stud.dao;

<span class="hljs-keyword">import</span> java.sql.SQLException;
<span class="hljs-keyword">import</span> java.util.UUID;

<span class="hljs-keyword">import</span> javax.sql.DataSource;

<span class="hljs-keyword">import</span> org.apache.commons.dbutils.QueryRunner;

<span class="hljs-keyword">import</span> cn.hncu.stud.domain.Book;
<span class="hljs-keyword">import</span> cn.hncu.stud.domain.Stud;

<span class="hljs-keyword">public</span> <span class="hljs-class"><span class="hljs-keyword">class</span> <span class="hljs-title">StudDaoJdbc</span> <span class="hljs-keyword">implements</span> <span class="hljs-title">StudDAO</span>{</span>
    <span class="hljs-keyword">private</span> DataSource dataSource = <span class="hljs-keyword">null</span>;<span class="hljs-comment">//依赖注入</span>
    <span class="hljs-keyword">public</span> DataSource <span class="hljs-title">getDataSource</span>() {
        <span class="hljs-keyword">return</span> dataSource;
    }

    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">setDataSource</span>(DataSource dataSource) {
        <span class="hljs-keyword">this</span>.dataSource = dataSource;
    }

    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">saveStud</span>(Stud stud) <span class="hljs-keyword">throws</span> SQLException {
        String uuid = UUID.randomUUID().toString().replaceAll(<span class="hljs-string">"-"</span>, <span class="hljs-string">""</span>);
        stud.setS_id(uuid);
        QueryRunner run = <span class="hljs-keyword">new</span> QueryRunner(dataSource);
        run.update(<span class="hljs-string">"insert into stud(s_id,s_name) values(?,?)"</span>, stud.getS_id(),stud.getS_name());
    }
}
</code></pre>



<h2 id="book的实现类">book的实现类</h2>



<pre class="prettyprint"><code class=" hljs java"><span class="hljs-keyword">package</span> cn.hncu.stud.dao;

<span class="hljs-keyword">import</span> java.sql.SQLException;

<span class="hljs-keyword">import</span> javax.sql.DataSource;

<span class="hljs-keyword">import</span> org.apache.commons.dbutils.QueryRunner;

<span class="hljs-keyword">import</span> cn.hncu.stud.domain.Book;

<span class="hljs-keyword">public</span> <span class="hljs-class"><span class="hljs-keyword">class</span> <span class="hljs-title">BookDaoJdbc</span> <span class="hljs-keyword">implements</span> <span class="hljs-title">BookDAO</span>{</span>
    <span class="hljs-keyword">private</span> DataSource dataSource = <span class="hljs-keyword">null</span>;<span class="hljs-comment">//依赖注入</span>

    <span class="hljs-keyword">public</span> DataSource <span class="hljs-title">getDataSource</span>() {
        <span class="hljs-keyword">return</span> dataSource;
    }

    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">setDataSource</span>(DataSource dataSource) {
        <span class="hljs-keyword">this</span>.dataSource = dataSource;
    }

    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">saveBook</span>(Book book) <span class="hljs-keyword">throws</span> SQLException {
        QueryRunner run = <span class="hljs-keyword">new</span> QueryRunner(dataSource);
        run.update(<span class="hljs-string">"insert into book(b_name) values(?)"</span>, book.getB_name());
    }

}
</code></pre>



<h1 id="测试">测试:</h1>

<p>打开页面输入：</p>

<p><img src="http://img.blog.csdn.net/20160904150255842" alt="" title=""></p>

<p>点按钮提交:</p>

<p><img src="http://img.blog.csdn.net/20160904150329608" alt="" title=""> <br>
service:cn.hncu.stud.service.SaveServiceImpl@4adeee3d这个输出是我在servlet中测试一个错误的时候的输出。</p>

<p>再看数据库的数据:</p>

<p><img src="http://img.blog.csdn.net/20160904152205939" alt="" title=""></p>

<p><img src="http://img.blog.csdn.net/20160904152213490" alt="" title=""></p>

<p>然后，我们来测试下，事务回滚情况：</p>

<p>因为service层是:</p>



<pre class="prettyprint"><code class=" hljs java"><span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">saveStudAndBook</span>(Stud stud, Book book) <span class="hljs-keyword">throws</span> SQLException {
        studDao.saveStud(stud);
        bookDao.saveBook(book);
    }</code></pre>

<p>后调用bookDao的，所以，我们让saveBook挂了，改一下saveBook的方法中sql语句为: <br>
<img src="http://img.blog.csdn.net/20160904152553679" alt="" title=""></p>

<p>这样，后面Book的存储肯定是出问题的。</p>

<p>再来测试: <br>
<img src="http://img.blog.csdn.net/20160904152717227" alt="" title=""></p>

<p>点添加。</p>

<p><img src="http://img.blog.csdn.net/20160904152752242" alt="" title=""></p>

<p>可以看到事务回滚了，但是看这里没用，我们去看下stud和book表有没有存储。当然book表肯定是不会被存储的，去看stud表就可以了：</p>

<p><img src="http://img.blog.csdn.net/20160904153127234" alt="" title=""></p>

<p>可以看到，李四这个用户并没有被保存，证明事务起作用了。</p>

<p>此实例适合初学Spring者学习哦。</p>

<p>本文章由<a href="https://chenhaoxiang.github.io/">[谙忆]</a>编写， 所有权利保留。 </p>

<blockquote cite="陈浩翔">
<p>转载请注明出处：<a href="http://blog.csdn.net/qq_26525215"><font color="green">http://blog.csdn.net/qq_26525215</font></a><br><br>
本文源自<strong>【<a href="http://blog.csdn.net/qq_26525215" target="_blank">大学之旅_谙忆的博客</a>】</strong></p>
</blockquote></div></body>
</html>