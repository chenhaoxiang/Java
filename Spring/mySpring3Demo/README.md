<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>【框架】[Spring3]下载安装、开源框架与IoC控制反转详解</title>
<link rel="stylesheet" href="https://stackedit.io/res-min/themes/base.css" />
<script type="text/javascript" src="https://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS_HTML"></script>
</head>
<body><div class="container"><blockquote cite="陈浩翔">
<p>转载请注明出处：<a href="http://blog.csdn.net/qq_26525215"><font color="green">http://blog.csdn.net/qq_26525215</font></a><br><br>
本文源自<strong>【<a href="http://blog.csdn.net/qq_26525215" target="_blank">大学之旅_谙忆的博客</a>】</strong></p>
</blockquote>

<p>昨天刚刚初学Spring3,也许Spring3有点老了哈，不过还是先把3学了再去学习4吧，首先先介绍一下如何去下载Spring的必须包吧。 <br>
(本篇博客适用于初学Spring的朋友)</p>

<p>java spring4现在不推荐使用xml配置文件… <br>
当然啦，这些知识点在Spring4还是可以用的。</p>

<p>不过我在这里详解的还是Spring3哈，见谅~</p>

<h1 id="下载springjar包文档">下载SpringJAR包/文档:</h1>

<p>Spring官网:<a href="http://spring.io/">http://spring.io/</a></p>

<p>Spring3.2版本以后(JAR/文档)的下载地址：<a href="http://repo.springsource.org/libs-release-local/org/springframework/spring/">http://repo.springsource.org/libs-release-local/org/springframework/spring/</a> <br>
（如果无法访问，请准备梯子）</p>

<p><img src="http://img.blog.csdn.net/20160831110935470" alt="" title=""></p>

<p>选择一个需要下载的版本进去: <br>
我选择的是最新的4.3.2版本.</p>

<p><img src="http://img.blog.csdn.net/20160831111033702" alt="" title=""></p>

<p>spring-framework-<em>*</em>(版本号).RELEASE-dist.zip 包含了Spring必须的JAR包、DOC文档以及源代码等。</p>

<p>下载完之后解压我们就可以在spring-framework-4.3.2.RELEASE\libs找到需要包和DOC文档以及源代码了。</p>

<p><img src="http://img.blog.csdn.net/20160831111405737" alt="" title=""></p>

<p>由于最新版本的已经提倡基于Java Config和注解的配置，不采用xml配置了，所以，我在这里准备的是Spring3.1.1版本的: <br>
下载链接: <br>
<a href="https://github.com/chenhaoxiang/Java/blob/master/Spring/spring-framework-3.1.1.RELEASE.zip">https://github.com/chenhaoxiang/Java/blob/master/Spring/spring-framework-3.1.1.RELEASE.zip</a></p>

<h1 id="spring开源框架">Spring开源框架</h1>



<h2 id="spring框架的起始">Spring框架的起始:</h2>

<p>Spring在英语中含义就是”春天”. <br>
对于Java EE开发者来说，Spring框架出现确实带来了一股全新的春天的气息。 <br>
早在2002年，Rod Johson在其编著的《Expert one to one J2EE design and development》书中，对Java EE框架臃肿、低效、脱离现实的种种现状提出了很多质疑，并积极寻求探索革新之道。 <br>
由他主导编写了interface21框架，从实际需求出发，着眼于轻便、灵巧，易于开发、测试和部署的轻量级开发框架。以interface21框架为基础，并集成了其它许多开源成果，于2004年3月24日，发布了1.0正式版取名为Spring。</p>



<h2 id="spring框架模块">Spring框架模块：</h2>

<p>Spring的核心是个轻量级容器，实现了IoC（控制翻转）模式的容器，基于此核心容器所建立的应用程序，可以达到程序组件的松散耦合。这些特性都使得整个应用程序维护简化。 Spring框架核心由下图所示的七个模块组成。</p>

<p><img src="http://img.blog.csdn.net/20160831112835165" alt="" title=""></p>

<p>现在来分别介绍一下这七个模块:</p>



<h3 id="1核心容器core">1、核心容器(Core)</h3>

<p>这是Spring框架最基础的部分，它提供了依赖注入（Dependency Injection）特征来实现容器对Bean的管理。这里最基本的概念是BeanFactory，它是任何Spring应用的核心。BeanFactory是工厂模式的一个实现，它使用IoC将应用配置和依赖说明从实际的应用代码中分离出来。</p>



<h3 id="2aop模块">2、AOP模块</h3>

<p>AOP即面向切面编程技术，Spring在它的AOP模块中提供了对面向切面编程的丰富支持。AOP允许通过分离应用的业务逻辑与系统级服务（例如安全和事务管理）进行内聚性的开发。应用对象只实现它们应该做的——完成业务逻辑——仅此而已。它们并不负责其它的系统级关注点，例如日志或事务支持。</p>



<h3 id="3对象关系映射集成模块orm">3、对象/关系映射集成模块ORM</h3>

<p>Hibernate是成熟的ORM产品，Spring并没有自己实现ORM框架而是集成了几个流行的ORM产品如Hibernate、JDO和iBATIS等。可以利用Spring对这些模块提供事务支持等。</p>



<h3 id="4jdbc抽象和dao模块">4、JDBC抽象和DAO模块</h3>

<p>Spring虽然集成了几个ORM产品，但也可以不选择这几款产品，因为Spring提供了JDBC和DAO模块。该模块对现有的JDBC技术进行了优化。你可以保持你的数据库访问代码干净简洁，并且可以防止因关闭数据库资源失败而引起的问题。</p>

<p>[JDBC DAO 抽象层提供了有意义的异常层次结构，可用该结构管管理异常处理和不同数据库供应商抛出的错误消息。异常层次结构简化了错误处理，并且极大地降低了需要编写的异常代码数量（例如打开和关闭连接）。SpringDAO的面向JDBC的异常遵从从通用的DAO异常层次结构]</p>

<h3 id="5spring的web模块">5、Spring的Web模块</h3>

<p>Web上下文模块建立于应用上下文模块之上，提供了一个适合于Web应用的上下文。另外，这个模块还提供了一些面向服务支持。例如：实现文件上传的multipart请求，它也提供了Spring和其它Web框架的集成，比如Struts、WebWork。</p>



<h3 id="6应用上下文context模块">6、应用上下文（Context）模块</h3>

<p>核心模块的BeanFactory使Spring成为一个容器，而上下文模块使它成为一个框架。Web上下文模块建立于应用上下文模块之上，提供了一个适合于Web应用的上下文。该模块还提供了一些面向服务支持这个模块扩展了BeanFactory的概念，增加了对国际化（I18N）消息、事件传播以及验证的支持。 </p>

<p>另外，这个模块还提供了许多企业服务，例如电子邮件、JNDI访问、EJB集成、远程以及时序调度（scheduling）服务。也包括对模版框架例如Velocity和FreeMarker集成的支持。</p>

<h3 id="7spring的mvc框架">7、Spring的MVC框架</h3>

<p>Spring为构建Web应用提供了一个功能全面的MVC框架。虽然Spring可以很容易地与其它MVC框架集成，例如Struts2，但Spring的MVC框架使用IoC对控制逻辑和业务对象提供了完全的分离。</p>



<h1 id="spring入门示例">Spring入门示例</h1>

<p>将需要的Jar包导入项目、</p>

<p>再准备一个xml配置文件: <br>
applicationContext.xml <br>
名字不一定要这个，自己可以随意改的，但建议使用这个名字。</p>



<h2 id="准备的模板">准备的模板:</h2>

<pre class="prettyprint"><code class=" hljs xml"><span class="hljs-pi">&lt;?xml version="1.0" encoding="UTF-8"?&gt;</span>
<span class="hljs-tag">&lt;<span class="hljs-title">beans</span> <span class="hljs-attribute">xmlns</span>=<span class="hljs-value">"http://www.springframework.org/schema/beans"</span>
        <span class="hljs-attribute">xmlns:xsi</span>=<span class="hljs-value">"http://www.w3.org/2001/XMLSchema-instance"</span>
        <span class="hljs-attribute">xmlns:context</span>=<span class="hljs-value">"http://www.springframework.org/schema/context"</span>
        <span class="hljs-attribute">xmlns:tx</span>=<span class="hljs-value">"http://www.springframework.org/schema/tx"</span>
        <span class="hljs-attribute">xsi:schemaLocation</span>=<span class="hljs-value">"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
                http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.0.xsd
                http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.0.xsd"</span>&gt;</span>

<span class="hljs-tag">&lt;/<span class="hljs-title">beans</span>&gt;</span>
</code></pre>

<p>将Spring的压缩包解压后：你可以在spring-framework-3.1.1.RELEASE\projects\org.springframework.web.portlet\src\test\java\org\springframework\web\portlet\context\WEB-INF目录下找到applicationContext.xml。我们只需要这个部分就可以了。 <br>
将文件建立在src目录下(建在另外的路径也可以)。</p>

<p><img src="http://img.blog.csdn.net/20160831133754660" alt="" title=""></p>

<p>导入必须的包，和建好applicationContext.xml文件。</p>

<p>1、编写一个普通的Java类（JavaBean）</p>



<pre class="prettyprint"><code class=" hljs cs">package cn.hncu.demo1.domain;

<span class="hljs-keyword">public</span> <span class="hljs-keyword">class</span> Person {
    <span class="hljs-keyword">private</span> String name;
    <span class="hljs-keyword">private</span> <span class="hljs-keyword">int</span> age;

    <span class="hljs-keyword">public</span> <span class="hljs-title">Person</span>() {
        System.<span class="hljs-keyword">out</span>.println(<span class="hljs-string">"执行Person的构造方法...."</span>);
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

    @Override
    <span class="hljs-keyword">public</span> String <span class="hljs-title">toString</span>() {
        <span class="hljs-keyword">return</span> <span class="hljs-string">"Person [name="</span> + name + <span class="hljs-string">", age="</span> + age + <span class="hljs-string">"]"</span>;
    }
}
</code></pre>

<p>2、在Spring配置文件applicationContext.xml。将JavaBean由Spring容器来管理。</p>



<pre class="prettyprint"><code class=" hljs xml"><span class="hljs-tag">&lt;<span class="hljs-title">beans</span> <span class="hljs-attribute">...</span>&gt;</span>
<span class="hljs-comment">&lt;!--scope属性值设为:prototype(每次获取都是一个新对象), request(同一个request中获取到的是同一个), session ,不设置，默认是单例 --&gt;</span>
    <span class="hljs-tag">&lt;<span class="hljs-title">bean</span> <span class="hljs-attribute">id</span>=<span class="hljs-value">"p"</span> <span class="hljs-attribute">class</span>=<span class="hljs-value">"cn.hncu.demo1.domain.Person"</span> <span class="hljs-attribute">scope</span>=<span class="hljs-value">"prototype"</span>&gt;</span>
    <span class="hljs-tag">&lt;/<span class="hljs-title">bean</span>&gt;</span>
<span class="hljs-tag">&lt;/<span class="hljs-title">beans</span>&gt;</span></code></pre>

<p>测试方法:</p>



<pre class="prettyprint"><code class=" hljs cs">    @Test
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">demo1</span>(){
        <span class="hljs-comment">//此方法已在3.0版本中过时，不建议使用</span>
        <span class="hljs-comment">//BeanFactory factory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));</span>

        <span class="hljs-comment">//Spring3.0建议采用下面这种方式使用容器</span>
        ApplicationContext ctx = <span class="hljs-keyword">new</span> ClassPathXmlApplicationContext(<span class="hljs-string">"applicationContext.xml"</span>);
        Person p = (Person) ctx.getBean(<span class="hljs-string">"p"</span>);<span class="hljs-comment">//通过配置的id获得，需要强转</span>
        System.<span class="hljs-keyword">out</span>.println(p);

        Person p2 = ctx.getBean(Person.class);<span class="hljs-comment">//通过Person的Class对象去获得，不需要强转</span>
        System.<span class="hljs-keyword">out</span>.println(p2);
    }</code></pre>

<p>输出结果:</p>

<p><img src="http://img.blog.csdn.net/20160831141440521" alt="" title=""></p>

<p>显然，Person只构造了一次。 <br>
由于我们没给初值，所以name是null，age因为是int型，所以是0.</p>

<p>现在我们在applicationContext.xml中来给它配置好初值，然后看:</p>

<pre class="prettyprint"><code class=" hljs xml">    <span class="hljs-comment">&lt;!--scope属性值设为:prototype(每次获取都是一个新对象), request(同一个request中获取到的是同一个), session ,不设置，默认是单例 --&gt;</span>
    <span class="hljs-tag">&lt;<span class="hljs-title">bean</span> <span class="hljs-attribute">id</span>=<span class="hljs-value">"p"</span> <span class="hljs-attribute">class</span>=<span class="hljs-value">"cn.hncu.demo1.domain.Person"</span>&gt;</span>
        <span class="hljs-comment">&lt;!-- 配置好初始值 --&gt;</span>
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"name"</span> <span class="hljs-attribute">value</span>=<span class="hljs-value">"张三"</span>&gt;</span><span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"age"</span> <span class="hljs-attribute">value</span>=<span class="hljs-value">"23"</span>&gt;</span><span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>
    <span class="hljs-tag">&lt;/<span class="hljs-title">bean</span>&gt;</span></code></pre>

<p>再一次的运行结果:</p>

<p><img src="http://img.blog.csdn.net/20160831141823257" alt="" title=""></p>



<h2 id="演示依赖xml注入">演示依赖(XML)注入</h2>

<p>在servlet注入servicce，Person， <br>
在servicce注入dao，</p>



<h3 id="logindao">LoginDAO</h3>



<pre class="prettyprint"><code class=" hljs java"><span class="hljs-keyword">package</span> cn.hncu.demo1.login.dao;

<span class="hljs-keyword">import</span> cn.hncu.demo1.domain.Person;

<span class="hljs-keyword">public</span> <span class="hljs-class"><span class="hljs-keyword">interface</span> <span class="hljs-title">LoginDAO</span> {</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">login</span>(Person p);
}
</code></pre>



<h3 id="logindaojdbc">LoginDaoJdbc</h3>



<pre class="prettyprint"><code class=" hljs java"><span class="hljs-keyword">package</span> cn.hncu.demo1.login.dao;

<span class="hljs-keyword">import</span> cn.hncu.demo1.domain.Person;

<span class="hljs-keyword">public</span> <span class="hljs-class"><span class="hljs-keyword">class</span> <span class="hljs-title">LoginDaoJdbc</span> <span class="hljs-keyword">implements</span> <span class="hljs-title">LoginDAO</span>{</span>

    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">login</span>(Person p) {
        System.out.println(<span class="hljs-string">"dao,到数据库中读取用户信息以进行登录...."</span>);     
        System.out.println(<span class="hljs-string">"dao中获取的用户输入信息:"</span>+p);     
    }

}
</code></pre>



<h3 id="iloginservice">ILoginService</h3>



<pre class="prettyprint"><code class=" hljs java"><span class="hljs-keyword">package</span> cn.hncu.demo1.login.service;

<span class="hljs-keyword">import</span> cn.hncu.demo1.domain.Person;

<span class="hljs-keyword">public</span> <span class="hljs-class"><span class="hljs-keyword">interface</span> <span class="hljs-title">ILoginService</span> {</span>

    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">login</span>(Person p);

}
</code></pre>



<h3 id="loginserviceimpl">LoginServiceImpl</h3>



<pre class="prettyprint"><code class=" hljs java"><span class="hljs-keyword">package</span> cn.hncu.demo1.login.service;

<span class="hljs-keyword">import</span> cn.hncu.demo1.domain.Person;
<span class="hljs-keyword">import</span> cn.hncu.demo1.login.dao.LoginDAO;


<span class="hljs-keyword">public</span> <span class="hljs-class"><span class="hljs-keyword">class</span> <span class="hljs-title">LoginServiceImpl</span> <span class="hljs-keyword">implements</span> <span class="hljs-title">ILoginService</span>{</span>
    <span class="hljs-keyword">private</span> LoginDAO dao = <span class="hljs-keyword">null</span>;
    <span class="hljs-comment">//如果要注入，需要注入的成员变量，必须写好set-get方法！</span>

    <span class="hljs-keyword">public</span> LoginDAO <span class="hljs-title">getDao</span>() {
        <span class="hljs-keyword">return</span> dao;
    }
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">setDao</span>(LoginDAO dao) {
        <span class="hljs-keyword">this</span>.dao = dao;
    }

    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">login</span>(Person p) {
        dao.login(p);
    }

}
</code></pre>



<h3 id="loginaction">LoginAction</h3>



<pre class="prettyprint"><code class=" hljs java"><span class="hljs-keyword">package</span> cn.hncu.demo1.login;

<span class="hljs-keyword">import</span> cn.hncu.demo1.domain.Person;
<span class="hljs-keyword">import</span> cn.hncu.demo1.login.service.ILoginService;

<span class="hljs-keyword">public</span> <span class="hljs-class"><span class="hljs-keyword">class</span> <span class="hljs-title">LoginAction</span> {</span>
    <span class="hljs-keyword">private</span> ILoginService service = <span class="hljs-keyword">null</span>;
    <span class="hljs-keyword">private</span> Person p = <span class="hljs-keyword">null</span>;
    <span class="hljs-comment">//如果要注入，需要注入的成员变量，必须写好set-get方法！</span>
    <span class="hljs-keyword">public</span> ILoginService <span class="hljs-title">getService</span>() {
        <span class="hljs-keyword">return</span> service;
    }
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">setService</span>(ILoginService service) {
        <span class="hljs-keyword">this</span>.service = service;
    }
    <span class="hljs-keyword">public</span> Person <span class="hljs-title">getP</span>() {
        <span class="hljs-keyword">return</span> p;
    }
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">setP</span>(Person p) {
        <span class="hljs-keyword">this</span>.p = p;
    }

    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">execute</span>(){
        service.login(p);
    }

}
</code></pre>

<p>需要依赖注入的变量一定要写好setter-getter 方法哦！！！</p>



<h3 id="测试方法">测试方法:</h3>



<pre class="prettyprint"><code class=" hljs java"><span class="hljs-annotation">@Test</span><span class="hljs-comment">//演示依赖(XML)注入</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">demo2</span>(){
        ApplicationContext ctx = <span class="hljs-keyword">new</span> ClassPathXmlApplicationContext(<span class="hljs-string">"applicationContext.xml"</span>);
        LoginAction action = ctx.getBean(LoginAction.class);
        <span class="hljs-comment">//LoginAction action = ctx.getBean("login",LoginAction.class);</span>
        action.execute();
    }
</code></pre>

<h3 id="配置文件">配置文件:</h3>



<pre class="prettyprint"><code class=" hljs xml"><span class="hljs-tag">&lt;<span class="hljs-title">beans</span> <span class="hljs-attribute">...</span>&gt;</span>
<span class="hljs-comment">&lt;!--scope属性值设为:prototype(每次获取都是一个新对象), request(同一个request中获取到的是同一个), session ,不设置，默认是单例 --&gt;</span>
    <span class="hljs-tag">&lt;<span class="hljs-title">bean</span> <span class="hljs-attribute">id</span>=<span class="hljs-value">"p"</span> <span class="hljs-attribute">class</span>=<span class="hljs-value">"cn.hncu.demo1.domain.Person"</span>&gt;</span>
        <span class="hljs-comment">&lt;!-- 配置好初始值 --&gt;</span>
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"name"</span> <span class="hljs-attribute">value</span>=<span class="hljs-value">"张三"</span>&gt;</span><span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"age"</span> <span class="hljs-attribute">value</span>=<span class="hljs-value">"23"</span>&gt;</span><span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>
    <span class="hljs-tag">&lt;/<span class="hljs-title">bean</span>&gt;</span>

    <span class="hljs-comment">&lt;!-- 注意这里的class是实现类，而不是接口哦 --&gt;</span>
    <span class="hljs-tag">&lt;<span class="hljs-title">bean</span> <span class="hljs-attribute">id</span>=<span class="hljs-value">"dao"</span> <span class="hljs-attribute">class</span>=<span class="hljs-value">"cn.hncu.demo1.login.dao.LoginDaoJdbc"</span>&gt;</span>
    <span class="hljs-tag">&lt;/<span class="hljs-title">bean</span>&gt;</span>

    <span class="hljs-comment">&lt;!-- 注意这里的class也是实现类，而不是接口哦 --&gt;</span>
    <span class="hljs-comment">&lt;!-- 如果换实现类了，只需把这里的class变了就可以了 --&gt;</span>
    <span class="hljs-tag">&lt;<span class="hljs-title">bean</span> <span class="hljs-attribute">id</span>=<span class="hljs-value">"s"</span> <span class="hljs-attribute">class</span>=<span class="hljs-value">"cn.hncu.demo1.login.service.LoginServiceImpl"</span>&gt;</span>
        <span class="hljs-comment">&lt;!-- 实现类中还有变量，ref是另外的bean的id-引用 --&gt;</span>
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"dao"</span> <span class="hljs-attribute">ref</span>=<span class="hljs-value">"dao"</span>&gt;</span><span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>      
    <span class="hljs-tag">&lt;/<span class="hljs-title">bean</span>&gt;</span>

    <span class="hljs-tag">&lt;<span class="hljs-title">bean</span> <span class="hljs-attribute">id</span>=<span class="hljs-value">"login"</span> <span class="hljs-attribute">class</span>=<span class="hljs-value">"cn.hncu.demo1.login.LoginAction"</span>&gt;</span>
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"service"</span> <span class="hljs-attribute">ref</span>=<span class="hljs-value">"s"</span>&gt;</span><span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"person"</span> <span class="hljs-attribute">ref</span>=<span class="hljs-value">"p"</span>&gt;</span><span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>
    <span class="hljs-tag">&lt;/<span class="hljs-title">bean</span>&gt;</span>
<span class="hljs-tag">&lt;/<span class="hljs-title">beans</span>&gt;</span></code></pre>



<h3 id="结构图">结构图：</h3>

<p><img src="http://img.blog.csdn.net/20160831143341070" alt="" title=""></p>

<h3 id="运行测试方法后的输出结果">运行测试方法后的输出结果:</h3>

<p><img src="http://img.blog.csdn.net/20160831150029058" alt="" title=""></p>

<p>现在增加一个DAO的实现类，可以在程序中无需代码改变，就可以注入不同实例. <br>
我们只需要改applicationContext.xml：</p>

<pre class="prettyprint"><code class=" hljs xml"><span class="hljs-comment">&lt;!-- 注意这里的class是实现类，而不是接口哦 --&gt;</span>
    <span class="hljs-comment">&lt;!-- 如果换实现类了，只需把这里的class变了就可以了,或者不动这里，增加一个bean --&gt;</span>
    <span class="hljs-tag">&lt;<span class="hljs-title">bean</span> <span class="hljs-attribute">id</span>=<span class="hljs-value">"dao"</span> <span class="hljs-attribute">class</span>=<span class="hljs-value">"cn.hncu.demo1.login.dao.LoginDaoJdbc2"</span>&gt;</span>
    <span class="hljs-tag">&lt;/<span class="hljs-title">bean</span>&gt;</span></code></pre>



<h3 id="增加的dao实现类">增加的DAO实现类：</h3>



<pre class="prettyprint"><code class=" hljs java"><span class="hljs-keyword">package</span> cn.hncu.demo1.login.dao;

<span class="hljs-keyword">import</span> cn.hncu.demo1.domain.Person;

<span class="hljs-keyword">public</span> <span class="hljs-class"><span class="hljs-keyword">class</span> <span class="hljs-title">LoginDaoJdbc2</span> <span class="hljs-keyword">implements</span> <span class="hljs-title">LoginDAO</span>{</span>

    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">login</span>(Person p) {
        System.out.println(<span class="hljs-string">"dao2,到数据库中读取用户信息以进行登录...."</span>);        
        System.out.println(<span class="hljs-string">"dao2中获取的用户输入信息:"</span>+p);        
    }

}
</code></pre>



<h3 id="改动后的演示结果">改动后的演示结果:</h3>

<p><img src="http://img.blog.csdn.net/20160831151340749" alt="" title=""></p>



<h1 id="spring-ioc-控制反转">Spring IOC 控制反转</h1>

<p>IoC(Inversion of Control)中文译为控制反转也可以叫做DI（Dependency Injection，依赖注入）。</p>

<p>控制反转模式的基本概念是：不直接创建对象，但是在xml配置文件中描述创建它们的方式。 <br>
在工程中使用该Bean时由Spring容器创建Bean的实例。在代码中不直接与对象和服务连接，但在配置文件中描述哪一个组件需要哪一项服务。</p>



<h2 id="1spring注入又称依赖注入di">1、Spring注入(又称依赖注入DI):</h2>

<p>其目的是为其中bean的属性赋值</p>

<p>通过Setter方法。（一般属性赋值即基本类型赋值示例）</p>

<p>第1步：编写JavaBean-必须写set方法</p>

<pre class="prettyprint"><code class=" hljs java"><span class="hljs-keyword">package</span> cn.hncu.demo2.domain;

<span class="hljs-keyword">public</span> <span class="hljs-class"><span class="hljs-keyword">class</span> <span class="hljs-title">Cat</span> {</span>
    <span class="hljs-keyword">private</span> String name;

    <span class="hljs-keyword">public</span> String <span class="hljs-title">getName</span>() {
        <span class="hljs-keyword">return</span> name;
    }

    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">setName</span>(String name) {
        <span class="hljs-keyword">this</span>.name = name;
    }

    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> String <span class="hljs-title">toString</span>() {
        <span class="hljs-keyword">return</span> <span class="hljs-string">"Cat [name="</span> + name + <span class="hljs-string">"]"</span>;
    }

}
</code></pre>

<p>第2步: 在配置文件中注入属性的初始值</p>



<pre class="prettyprint"><code class=" hljs xml"><span class="hljs-pi">&lt;?xml version="1.0" encoding="UTF-8"?&gt;</span>
<span class="hljs-tag">&lt;<span class="hljs-title">beans</span> <span class="hljs-attribute">xmlns</span>=<span class="hljs-value">"http://www.springframework.org/schema/beans"</span>
        <span class="hljs-attribute">xmlns:xsi</span>=<span class="hljs-value">"http://www.w3.org/2001/XMLSchema-instance"</span>
        <span class="hljs-attribute">xmlns:context</span>=<span class="hljs-value">"http://www.springframework.org/schema/context"</span>
        <span class="hljs-attribute">xmlns:tx</span>=<span class="hljs-value">"http://www.springframework.org/schema/tx"</span>
        <span class="hljs-attribute">xsi:schemaLocation</span>=<span class="hljs-value">"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
                http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.0.xsd
                http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.0.xsd"</span>&gt;</span>

    <span class="hljs-tag">&lt;<span class="hljs-title">bean</span> <span class="hljs-attribute">id</span>=<span class="hljs-value">"cat"</span> <span class="hljs-attribute">class</span>=<span class="hljs-value">"cn.hncu.demo2.domain.Cat"</span>&gt;</span>
    <span class="hljs-comment">&lt;!-- 初始化name --&gt;</span>
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"name"</span> <span class="hljs-attribute">value</span>=<span class="hljs-value">"黑猫"</span>&gt;</span><span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>
    <span class="hljs-tag">&lt;/<span class="hljs-title">bean</span>&gt;</span>

<span class="hljs-tag">&lt;/<span class="hljs-title">beans</span>&gt;</span>
</code></pre>

<p>这样拿到的Cat对象就有初始值了，name的初始值为”黑猫”。</p>



<h2 id="复杂数据类型的初始化">复杂数据类型的初始化：</h2>

<p>例如，含有List，Map，Set，或者其他对象的数据，这样的bean对象如何初始化值呢。</p>

<p>结构如下:</p>

<p><img src="http://img.blog.csdn.net/20160831154053105" alt="" title=""></p>

<p>Cat类就是前面那个。</p>



<h3 id="user">User</h3>



<pre class="prettyprint"><code class=" hljs cs">package cn.hncu.demo2.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

<span class="hljs-keyword">public</span> <span class="hljs-keyword">class</span> User {
    <span class="hljs-keyword">private</span> String name;
    <span class="hljs-keyword">private</span> Integer age;
    <span class="hljs-keyword">private</span> List&lt;Object&gt; pets;
    <span class="hljs-keyword">private</span> Map&lt;String, Object&gt; map;
    <span class="hljs-keyword">private</span> Set&lt;Object&gt; <span class="hljs-keyword">set</span>;
    <span class="hljs-keyword">private</span> Object objs[];
    <span class="hljs-keyword">private</span> Cat cat;


    <span class="hljs-keyword">public</span> String <span class="hljs-title">getName</span>() {
        <span class="hljs-keyword">return</span> name;
    }

    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">setName</span>(String name) {
        <span class="hljs-keyword">this</span>.name = name;
    }

    <span class="hljs-keyword">public</span> Integer <span class="hljs-title">getAge</span>() {
        <span class="hljs-keyword">return</span> age;
    }

    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">setAge</span>(Integer age) {
        <span class="hljs-keyword">this</span>.age = age;
    }


    <span class="hljs-keyword">public</span> List&lt;Object&gt; <span class="hljs-title">getPets</span>() {
        <span class="hljs-keyword">return</span> pets;
    }

    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">setPets</span>(List&lt;Object&gt; pets) {
        <span class="hljs-keyword">this</span>.pets = pets;
    }


    <span class="hljs-keyword">public</span> Map&lt;String, Object&gt; <span class="hljs-title">getMap</span>() {
        <span class="hljs-keyword">return</span> map;
    }

    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">setMap</span>(Map&lt;String, Object&gt; map) {
        <span class="hljs-keyword">this</span>.map = map;
    }



    <span class="hljs-keyword">public</span> Set&lt;Object&gt; <span class="hljs-title">getSet</span>() {
        <span class="hljs-keyword">return</span> <span class="hljs-keyword">set</span>;
    }

    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">setSet</span>(Set&lt;Object&gt; <span class="hljs-keyword">set</span>) {
        <span class="hljs-keyword">this</span>.<span class="hljs-keyword">set</span> = <span class="hljs-keyword">set</span>;
    }



    <span class="hljs-keyword">public</span> Object[] <span class="hljs-title">getObjs</span>() {
        <span class="hljs-keyword">return</span> objs;
    }

    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">setObjs</span>(Object[] objs) {
        <span class="hljs-keyword">this</span>.objs = objs;
    }

    <span class="hljs-keyword">public</span> Cat <span class="hljs-title">getCat</span>() {
        <span class="hljs-keyword">return</span> cat;
    }

    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">setCat</span>(Cat cat) {
        <span class="hljs-keyword">this</span>.cat = cat;
    }

    @Override
    <span class="hljs-keyword">public</span> String <span class="hljs-title">toString</span>() {
        <span class="hljs-keyword">return</span> <span class="hljs-string">"User [name="</span> + name + <span class="hljs-string">", age="</span> + age + <span class="hljs-string">", pets="</span> + pets
                + <span class="hljs-string">"\r\n map="</span> + map + <span class="hljs-string">"\r\n set="</span> + <span class="hljs-keyword">set</span> + <span class="hljs-string">"\r\n objs="</span>
                + Arrays.toString(objs) + <span class="hljs-string">", cat="</span> + cat + <span class="hljs-string">"]"</span>;
    }

}
</code></pre>



<h3 id="配置文件-1">配置文件</h3>

<p>在这里我取名为：applicationContext2.xml了。</p>



<pre class="prettyprint"><code class=" hljs xml"><span class="hljs-pi">&lt;?xml version="1.0" encoding="UTF-8"?&gt;</span>
<span class="hljs-tag">&lt;<span class="hljs-title">beans</span> <span class="hljs-attribute">xmlns</span>=<span class="hljs-value">"http://www.springframework.org/schema/beans"</span>
        <span class="hljs-attribute">xmlns:xsi</span>=<span class="hljs-value">"http://www.w3.org/2001/XMLSchema-instance"</span>
        <span class="hljs-attribute">xmlns:context</span>=<span class="hljs-value">"http://www.springframework.org/schema/context"</span>
        <span class="hljs-attribute">xmlns:tx</span>=<span class="hljs-value">"http://www.springframework.org/schema/tx"</span>
        <span class="hljs-attribute">xsi:schemaLocation</span>=<span class="hljs-value">"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
                http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.0.xsd
                http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.0.xsd"</span>&gt;</span>


    <span class="hljs-tag">&lt;<span class="hljs-title">bean</span> <span class="hljs-attribute">id</span>=<span class="hljs-value">"cat"</span> <span class="hljs-attribute">class</span>=<span class="hljs-value">"cn.hncu.demo2.domain.Cat"</span>&gt;</span>
    <span class="hljs-comment">&lt;!-- 初始化name --&gt;</span>
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"name"</span> <span class="hljs-attribute">value</span>=<span class="hljs-value">"黑猫"</span>&gt;</span><span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>
    <span class="hljs-tag">&lt;/<span class="hljs-title">bean</span>&gt;</span>

    <span class="hljs-tag">&lt;<span class="hljs-title">bean</span> <span class="hljs-attribute">id</span>=<span class="hljs-value">"user"</span> <span class="hljs-attribute">class</span>=<span class="hljs-value">"cn.hncu.demo2.domain.User"</span>&gt;</span>
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"name"</span> <span class="hljs-attribute">value</span>=<span class="hljs-value">"Jack"</span>&gt;</span><span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"age"</span> <span class="hljs-attribute">value</span>=<span class="hljs-value">"25"</span>&gt;</span><span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>

        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"pets"</span>&gt;</span>
            <span class="hljs-tag">&lt;<span class="hljs-title">list</span>&gt;</span>
                <span class="hljs-tag">&lt;<span class="hljs-title">value</span>&gt;</span>cat<span class="hljs-tag">&lt;/<span class="hljs-title">value</span>&gt;</span>
                <span class="hljs-tag">&lt;<span class="hljs-title">value</span>&gt;</span>dog<span class="hljs-tag">&lt;/<span class="hljs-title">value</span>&gt;</span>
                <span class="hljs-tag">&lt;<span class="hljs-title">value</span>&gt;</span>tiger<span class="hljs-tag">&lt;/<span class="hljs-title">value</span>&gt;</span>
            <span class="hljs-tag">&lt;/<span class="hljs-title">list</span>&gt;</span>
        <span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>

        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"map"</span>&gt;</span>
            <span class="hljs-tag">&lt;<span class="hljs-title">map</span>&gt;</span>
                <span class="hljs-tag">&lt;<span class="hljs-title">entry</span> <span class="hljs-attribute">key</span>=<span class="hljs-value">"name"</span> <span class="hljs-attribute">value</span>=<span class="hljs-value">"中国"</span>&gt;</span><span class="hljs-tag">&lt;/<span class="hljs-title">entry</span>&gt;</span>
                <span class="hljs-tag">&lt;<span class="hljs-title">entry</span> <span class="hljs-attribute">key</span>=<span class="hljs-value">"age"</span> <span class="hljs-attribute">value</span>=<span class="hljs-value">"67"</span>&gt;</span><span class="hljs-tag">&lt;/<span class="hljs-title">entry</span>&gt;</span>
                <span class="hljs-comment">&lt;!-- ref是引用 --&gt;</span>
                <span class="hljs-tag">&lt;<span class="hljs-title">entry</span> <span class="hljs-attribute">key</span>=<span class="hljs-value">"cat"</span> <span class="hljs-attribute">value-ref</span>=<span class="hljs-value">"cat"</span>&gt;</span><span class="hljs-tag">&lt;/<span class="hljs-title">entry</span>&gt;</span>
            <span class="hljs-tag">&lt;/<span class="hljs-title">map</span>&gt;</span>
        <span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"set"</span>&gt;</span>
         <span class="hljs-tag">&lt;<span class="hljs-title">set</span>&gt;</span>
            <span class="hljs-comment">&lt;!-- 引用 --&gt;</span>
             <span class="hljs-tag">&lt;<span class="hljs-title">ref</span> <span class="hljs-attribute">bean</span>=<span class="hljs-value">"cat"</span>/&gt;</span>
             <span class="hljs-tag">&lt;<span class="hljs-title">value</span>&gt;</span>aaa<span class="hljs-tag">&lt;/<span class="hljs-title">value</span>&gt;</span>
             <span class="hljs-tag">&lt;<span class="hljs-title">value</span>&gt;</span>bbb<span class="hljs-tag">&lt;/<span class="hljs-title">value</span>&gt;</span>
         <span class="hljs-tag">&lt;/<span class="hljs-title">set</span>&gt;</span>
       <span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"objs"</span>&gt;</span>
            <span class="hljs-comment">&lt;!-- 数组 --&gt;</span>
            <span class="hljs-tag">&lt;<span class="hljs-title">array</span>&gt;</span>
                <span class="hljs-tag">&lt;<span class="hljs-title">value</span>&gt;</span>hrllo<span class="hljs-tag">&lt;/<span class="hljs-title">value</span>&gt;</span>
                <span class="hljs-tag">&lt;<span class="hljs-title">ref</span> <span class="hljs-attribute">bean</span>=<span class="hljs-value">"cat"</span>/&gt;</span>
                <span class="hljs-tag">&lt;<span class="hljs-title">list</span>&gt;</span>
                    <span class="hljs-tag">&lt;<span class="hljs-title">value</span>&gt;</span>obj1<span class="hljs-tag">&lt;/<span class="hljs-title">value</span>&gt;</span>
                    <span class="hljs-tag">&lt;<span class="hljs-title">value</span>&gt;</span>obj2<span class="hljs-tag">&lt;/<span class="hljs-title">value</span>&gt;</span>
                <span class="hljs-tag">&lt;/<span class="hljs-title">list</span>&gt;</span>
                <span class="hljs-tag">&lt;<span class="hljs-title">bean</span> <span class="hljs-attribute">class</span>=<span class="hljs-value">"cn.hncu.demo2.domain.Cat"</span>&gt;</span>
                    <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"name"</span> <span class="hljs-attribute">value</span>=<span class="hljs-value">"白猫"</span>&gt;</span><span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>
                <span class="hljs-tag">&lt;/<span class="hljs-title">bean</span>&gt;</span>
            <span class="hljs-tag">&lt;/<span class="hljs-title">array</span>&gt;</span>
        <span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>
    <span class="hljs-tag">&lt;/<span class="hljs-title">bean</span>&gt;</span>
<span class="hljs-tag">&lt;/<span class="hljs-title">beans</span>&gt;</span>
</code></pre>

<h3 id="测试类">测试类:</h3>



<pre class="prettyprint"><code class=" hljs avrasm">package cn<span class="hljs-preprocessor">.hncu</span><span class="hljs-preprocessor">.demo</span>2<span class="hljs-comment">;</span>

import org<span class="hljs-preprocessor">.junit</span><span class="hljs-preprocessor">.Test</span><span class="hljs-comment">;</span>
import org<span class="hljs-preprocessor">.springframework</span><span class="hljs-preprocessor">.context</span><span class="hljs-preprocessor">.ApplicationContext</span><span class="hljs-comment">;</span>
import org<span class="hljs-preprocessor">.springframework</span><span class="hljs-preprocessor">.context</span><span class="hljs-preprocessor">.support</span><span class="hljs-preprocessor">.ClassPathXmlApplicationContext</span><span class="hljs-comment">;</span>

import cn<span class="hljs-preprocessor">.hncu</span><span class="hljs-preprocessor">.demo</span>2<span class="hljs-preprocessor">.domain</span><span class="hljs-preprocessor">.User</span><span class="hljs-comment">;</span>

public class Demo2 {

    //演示spring中&lt;bean&gt;属性注入的一些细节
    @Test
    public void demo(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext(<span class="hljs-string">"applicationContext2.xml"</span>)<span class="hljs-comment">;</span>
        User user = ctx<span class="hljs-preprocessor">.getBean</span>(<span class="hljs-string">"user"</span>,User<span class="hljs-preprocessor">.class</span>)<span class="hljs-comment">;</span>
        System<span class="hljs-preprocessor">.out</span><span class="hljs-preprocessor">.println</span>(user)<span class="hljs-comment">;</span>
    }


}
</code></pre>



<h3 id="测试结果">测试结果:</h3>

<p><img src="http://img.blog.csdn.net/20160831154330481" alt="" title=""></p>

<p>如果对于这个User的类的赋值搞懂了，我想应该在这块就会很熟练了。</p>



<h1 id="完整的项目源码">完整的项目源码:</h1>

<p>本文章由[谙忆]编写， 所有权利保留。 </p>

<blockquote cite="陈浩翔">
<p>转载请注明出处：<a href="http://blog.csdn.net/qq_26525215"><font color="green">http://blog.csdn.net/qq_26525215</font></a><br><br>
本文源自<strong>【<a href="http://blog.csdn.net/qq_26525215" target="_blank">大学之旅_谙忆的博客</a>】</strong></p>
</blockquote></div></body>
</html>