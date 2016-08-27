<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>【框架】[Hibernate]构架知识点详细解析</title>
<link rel="stylesheet" href="https://stackedit.io/res-min/themes/base.css" />
<script type="text/javascript" src="https://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS_HTML"></script>
</head>
<body><div class="container"><blockquote cite="陈浩翔">
<p>转载请注明出处：<a href="http://blog.csdn.net/qq_26525215"><font color="green">http://blog.csdn.net/qq_26525215</font></a><br><br>
本文源自<strong>【<a href="http://blog.csdn.net/qq_26525215" target="_blank">大学之旅_谙忆的博客</a>】</strong></p>
</blockquote>



<h1 id="hibernate介绍">Hibernate介绍:</h1>

<p>Hibernate是一个开放源码的、非常优秀、成熟的O/R Mapping框架。它提供了强大、高性能的Java对象和关系数据的持久化和查询功能。</p>

<p>O/R Mapping <br>
对象关系映射（Object Relational Mapping，简称ORM）技术，是通过使用描述对象和数据库之间映射的元数据，将Java程序中的对象自动持久化到关系数据库中。 <br>
对象和关系数据是业务实体的两种表现形式，业务实体在内存中表现为对象，在数据库中表现为关系数据。内存中的对象之间存在关联和继承关系，而在数据库中，关系数据无法直接表达多对多关联和继承关系。因此，对象-关系映射(ORM)系统一般以中间件的形式存在，主要实现程序对象到关系数据库数据的映射。</p>

<p>Hibernate 只是一个将持久化类与数据库表相映射的工具，每个持久化类实例均对应于数据库表中的一条数据行。可以使用面向对象的方法操作此持久化类实例，完成对数据库表的插入、删除、修改等操作。</p>

<p>利用Hibernate操作数据库，我们通过应用程序经过Hibernate持久层来访问数据库，其实Hibernate完成了以前JDBC的功能，不过Hibernate使用面向对象的方法操作数据库。</p>



<h2 id="hibernate体系结构图">Hibernate体系结构图:</h2>

<p><img src="http://img.blog.csdn.net/20160827222841712" alt="" title=""></p>



<h1 id="hibernate构架入门步骤">Hibernate构架入门步骤</h1>

<p>第1步： 先建一个Java工程导入使用Hibernate最小必要包。 <br>
一般在解压后的lib目录下的 <br>
<img src="http://img.blog.csdn.net/20160827224843692" alt="" title=""> <br>
required目录下。 <br>
可以到官方网站 <a href="http://hibernate.org/orm/downloads/">http://hibernate.org/orm/downloads/</a> 下载Hibernate最新的zip压缩包。 <br>
如果访问数据库，则还需要导入数据库的驱动包。 <br>
mysql-connector-java-5.1.39 .zip下载地址:</p>

<blockquote cite="陈浩翔">
<p><a href="https://github.com/chenhaoxiang/Java/blob/master/Database-support-package/mysql-connector-java-5.1.39%20.zip"><font color="red">–&gt;点击下载数据库5.1.39驱动包–</font></a><br>
</p></blockquote>

<p>第2步：在src创建配置文件hibernate.cfg.xml，放置在src目录下。</p>

<p>第3步：编写一个会话工厂类。通过会话工厂类产生一个会话Session对象。Session对象是Hibernate的核心。任何对数据库操作都在会话中进行的。</p>

<p>对于熟悉C3P0的朋友，可以按下面的理解: <br>
(配置文件hibernate.cfg.xml可以类比c3p0.properties的配置文件，HibernateSessionFactory可以类比C3p0Pool，SessionFactory可以类比C3p0中的DataSource，Session可以类比Connection去理解)</p>

<p>第4步：编写POJO类以及映射文件。 <br>
POJO(POJO（Plain Ordinary Java Object）简单的Java对象，实际就是普通JavaBeans)</p>

<p>第5步：编写测试文件</p>

<h2 id="hibernatecfgxml配置文件详解">hibernate.cfg.xml配置文件详解</h2>

<p>hibernate.cfg.xml其实这个名字自己可以随便取的，但是大家都这么取名，于是形成了一个习惯了！！！</p>

<pre class="prettyprint"><code class=" hljs xml"><span class="hljs-pi">&lt;?xml version='1.0' encoding='UTF-8'?&gt;</span>
<span class="hljs-comment">&lt;!--标准的XML文件的起始行，version='1.0'表明XML的版本，encoding='UTF-8'表明XML文件的编码方式 --&gt;</span>

<span class="hljs-comment">&lt;!--表明解析本XML文件的DTD文档位置，DTD是Document Type Definition 的缩写,即文档类型的定义,
XML解析器使用DTD文档来检查XML文件的合法性。
hibernate.sourceforge.net/hibernate-configuration-3.0dtd可以在Hibernate3.1.3软件包中的
src\org\hibernate目录中找到此文件--&gt;</span>   
<span class="hljs-doctype">&lt;!DOCTYPE hibernate-configuration PUBLIC
          "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
          "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd"&gt;</span>

<span class="hljs-comment">&lt;!--声明Hibernate配置文件的开始 --&gt;</span>
<span class="hljs-tag">&lt;<span class="hljs-title">hibernate-configuration</span>&gt;</span>

    <span class="hljs-comment">&lt;!--表明以下的配置是针对session-factory配置的，SessionFactory是Hibernate中的一个类，
    这个类主要负责保存HIbernate的配置信息，以及对Session的操作--&gt;</span>   
    <span class="hljs-tag">&lt;<span class="hljs-title">session-factory</span>&gt;</span>
        <span class="hljs-comment">&lt;!--配置数据库的驱动程序，Hibernate在连接数据库时，需要用到数据库的驱动程序-必须 --&gt;</span>
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"connection.driver_class"</span>&gt;</span>
            com.mysql.jdbc.Driver
        <span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>
        <span class="hljs-comment">&lt;!--设置数据库的连接url:jdbc:mysql://127.0.0.1:3306/hib,其中127.0.0.1表示mysql服务器名称,
        3306是连接端口，此处为本机,hib是数据库名-必须 --&gt;</span>
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"connection.url"</span>&gt;</span>
            jdbc:mysql://127.0.0.1:3306/hib
        <span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>
        <span class="hljs-comment">&lt;!-- 设置数据库的用户名 -必须 --&gt;</span>
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"connection.username"</span>&gt;</span>root<span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>
        <span class="hljs-comment">&lt;!-- 设置数据库的密码 -必须 --&gt;</span>
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"connection.password"</span>&gt;</span>1234<span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>
        <span class="hljs-comment">&lt;!--数据库连接池的大小 --&gt;</span>
        <span class="hljs-comment">&lt;!-- &lt;property name="connection.pool.size"&gt;20 &lt;/property&gt; 与下句是一样的 --&gt;</span>
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"hibernate.connection.pool.size"</span>&gt;</span>20 <span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>

        <span class="hljs-comment">&lt;!--是否在后台显示Hibernate用到的SQL语句，开发时设置为true，便于查错，
        程序运行时可以在Eclipse的控制台显示Hibernate的执行Sql语句。
        项目部署后可以设置为false，提高运行效率--&gt;</span>   
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"hibernate.show_sql"</span>&gt;</span>true <span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>   

        <span class="hljs-comment">&lt;!--jdbc.fetch_size是指Hibernate每次从数据库中取出并放到JDBC的Statement中的记录条数。
        Fetch Size设的越大，读数据库的次数越少，速度越快，Fetch Size越小，读数据库的次数越多，速度越慢--&gt;</span>   
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"jdbc.fetch_size"</span>&gt;</span>50 <span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span> 

        <span class="hljs-comment">&lt;!--jdbc.batch_size是指Hibernate批量插入,删除和更新时每次操作的记录数。
        Batch Size越大，批量操作的向数据库发送Sql的次数越少，速度就越快，同样耗用内存就越大--&gt;</span>   
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"jdbc.batch_size"</span>&gt;</span>23 <span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>  

        <span class="hljs-comment">&lt;!--jdbc.use_scrollable_resultset是否允许Hibernate用JDBC的可滚动的结果集。
        对分页的结果集。对分页时的设置非常有帮助--&gt;</span>   
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"jdbc.use_scrollable_resultset"</span>&gt;</span>false <span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span> 

        <span class="hljs-comment">&lt;!--connection.useUnicode连接数据库时是否使用Unicode编码--&gt;</span>   
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"connection.useUnicode"</span>&gt;</span>true<span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>   
        <span class="hljs-comment">&lt;!--connection.characterEncoding连接数据库时数据的传输字符集编码方式--&gt;</span>   
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"connection.characterEncoding"</span>&gt;</span>utf-8<span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span> 


<span class="hljs-comment">&lt;!--hibernate.dialect 只是Hibernate使用的数据库方言,就是要用Hibernate连接那种类型的数据库服务器。--&gt;</span>   
        <span class="hljs-comment">&lt;!--这句不用管，照写就是 -必须 --&gt;</span>
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"dialect"</span>&gt;</span>
            org.hibernate.dialect.MySQLDialect
        <span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>

        <span class="hljs-comment">&lt;!-- 指定映射文件为“cn/hncu/domain/Student.hbm.xml”，可以有多个 -用到了就必须 --&gt;</span>
        <span class="hljs-tag">&lt;<span class="hljs-title">mapping</span> <span class="hljs-attribute">resource</span>=<span class="hljs-value">"cn/hncu/domain/Student.hbm.xml"</span> /&gt;</span>
    <span class="hljs-tag">&lt;/<span class="hljs-title">session-factory</span>&gt;</span>

<span class="hljs-tag">&lt;/<span class="hljs-title">hibernate-configuration</span>&gt;</span></code></pre>

<p>简单配置版:</p>



<pre class="prettyprint"><code class=" hljs xml"><span class="hljs-pi">&lt;?xml version='1.0' encoding='UTF-8'?&gt;</span>
<span class="hljs-doctype">&lt;!DOCTYPE hibernate-configuration PUBLIC
          "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
          "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd"&gt;</span>
<span class="hljs-tag">&lt;<span class="hljs-title">hibernate-configuration</span>&gt;</span>
<span class="hljs-comment">&lt;!-- 这是最简单的配置版！！！ --&gt;</span>
<span class="hljs-tag">&lt;<span class="hljs-title">session-factory</span>&gt;</span>
    <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"connection.driver_class"</span>&gt;</span>
        com.mysql.jdbc.Driver
    <span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>
    <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"connection.url"</span>&gt;</span>
        jdbc:mysql://127.0.0.1:3306/hib
    <span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>
    <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"connection.username"</span>&gt;</span>root<span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>
    <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"connection.password"</span>&gt;</span>1234<span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>

    <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"dialect"</span>&gt;</span>
        org.hibernate.dialect.MySQLDialect
    <span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>

    <span class="hljs-tag">&lt;<span class="hljs-title">mapping</span> <span class="hljs-attribute">resource</span>=<span class="hljs-value">"cn/hncu/domain/Student.hbm.xml"</span>/&gt;</span>
<span class="hljs-tag">&lt;/<span class="hljs-title">session-factory</span>&gt;</span>

<span class="hljs-tag">&lt;/<span class="hljs-title">hibernate-configuration</span>&gt;</span></code></pre>

<h2 id="hibernate核心接口">Hibernate核心接口</h2>

<h3 id="1-configuration接口">(1)  Configuration接口</h3>

<p>Configuration 接口负责管理Hibernate 的配置信息。为了能够连上数据库必须配置一些属性，这些属性包括：  <br>
数据库URL <br>
数据库用户 <br>
数据库用户密码 <br>
数据库JDBC驱动类 <br>
数据库dialect，用于对特定数据库提供支持，其中包含了针对特定数据库特性的实现。 </p>



<pre class="prettyprint"><code class=" hljs vhdl"> <span class="hljs-comment">/*创建一个配置对象，读取配置文件*/</span>
 <span class="hljs-keyword">Configuration</span> config = <span class="hljs-keyword">new</span> <span class="hljs-keyword">Configuration</span>();
 config.configure(<span class="hljs-string">"/hibernate.cfg.xml"</span>);
</code></pre>



<h3 id="2-sessionfactory接口">(2)  SessionFactory接口</h3>

<p>应用程序从SessionFactory（会话工厂）里获得Session(会话)实例。 <br>
这里用到了一个设计模式即工厂模式，用户程序从工厂类SessionFactory中取得Session的实例。 <br>
SessionFactory不是轻量级的。它占的资源比较多，所以它应该能在整个应用中共享。 <br>
一个项目通常只需要一个SessionFactory就够了，但是当项目要操作多个数据库时，必须为每个数据库指定一个SessionFactory。 <br>
会话工厂缓存了生成的SQL语句和Hibernate在运行时使用的映射元数据。它也保存了在一个工作单元中读入的数据并且可能在以后的工作单元中被重用（只有类和集合映射指定了使用这种二级缓存时才会如此）Session类。</p>

<pre class="prettyprint"><code class=" hljs avrasm"><span class="hljs-comment">/*通过配置对象产生一个会话工厂*/</span>
    SessionFactory factory=config<span class="hljs-preprocessor">.buildSessionFactory</span>()<span class="hljs-comment">;</span>
</code></pre>



<h3 id="3-session接口">(3)  Session接口</h3>

<p>该接口是Hibernate使用最多的接口。Session不是线程安全的，它代表与数据库之间的一次操作。 <br>
Session是持久层操作的基础，相当于JDBC中的Connection。然而在Hibernate中，实例化的Session是一个轻量级的类，创建和销毁它都不会占用很多资源。 <br>
Session通过SessionFactory打开，在所有的工作完成后，需要关闭。但如果在程序中，不断地创建以及销毁Session对象，则会给系统带来不良影响。所以有时需要考虑session的管理合理的创建合理的销毁。</p>



<pre class="prettyprint"><code class=" hljs avrasm"><span class="hljs-comment">/*通过工厂产生一个会话*/</span>
     Session session=factory<span class="hljs-preprocessor">.openSession</span>()<span class="hljs-comment">;</span>
</code></pre>



<h3 id="4-query类">(4)  Query类</h3>

<p>Query类可以很方便地对数据库及持久对象进行查询，它可以有两种表达方式：查询语句使用HQL(面向”值对象”，HQL语言) （HQL是Hibernate Query Lanaguage简称是Hibernate配备了一种非常强大的查询语言，类似于SQL）或者本地数据库的SQL语句(面向”数据表”，SQL语言)编写。</p>



<pre class="prettyprint"><code class=" hljs php"><span class="hljs-comment">/*通过会话产生一个查询对象*/</span>
        Query query = session.createQuery(<span class="hljs-string">"from Student"</span>);<span class="hljs-comment">//Student是POJO类，需要在配置文件中设置好映射</span>
        <span class="hljs-comment">/*通过查询对象查询数据库，返回集合*/</span>
        <span class="hljs-keyword">List</span> <span class="hljs-keyword">list</span> = query.<span class="hljs-keyword">list</span>();
        <span class="hljs-keyword">for</span> (int i = <span class="hljs-number">0</span>; i &lt; <span class="hljs-keyword">list</span>.size(); i++) {
            Student student= (Student) <span class="hljs-keyword">list</span>.get(i);
            System.out.println(Student.getDname());
        }</code></pre>



<h3 id="5-transaction接口">(5)  Transaction接口</h3>

<p>如果你向数据库中增加数据或修改数据时，需要使用事务处理，这时你需要Transaction接口。 <br>
 Transaction接口是对实际事务实现的一个抽象，该接口可以实现JDBC的事务、JTA中的UserTransaction、甚至可以是CORBA事务等跨容器的事务。 <br>
 之所以这样设计是能让开发者能够使用一个统一事务的操作界面，使得自己的项目可以在不同的环境和容器之间方便地移值。</p>



<h2 id="一个完整示例显示了hibernate编程基本思路">一个完整示例,显示了Hibernate编程基本思路</h2>



<h3 id="首先准备好实例数据库">首先，准备好实例数据库：</h3>

<pre class="prettyprint"><code class=" hljs sql"><span class="hljs-operator"><span class="hljs-keyword">create</span> <span class="hljs-keyword">database</span> hib charset=utf8;</span>
use hib;
<span class="hljs-operator"><span class="hljs-keyword">create</span> <span class="hljs-keyword">table</span> students(
    sId <span class="hljs-keyword">varchar</span>(<span class="hljs-number">8</span>) <span class="hljs-keyword">primary</span> <span class="hljs-keyword">key</span>,
    sName <span class="hljs-keyword">varchar</span>(<span class="hljs-number">40</span>),
    sAge <span class="hljs-keyword">int</span>
);</span>
<span class="hljs-operator"><span class="hljs-keyword">insert</span> <span class="hljs-keyword">into</span> students(sId,sName,sAge) <span class="hljs-keyword">values</span>(<span class="hljs-string">'S001'</span>,<span class="hljs-string">'Jack'</span>,<span class="hljs-number">23</span>);</span>
<span class="hljs-operator"><span class="hljs-keyword">insert</span> <span class="hljs-keyword">into</span> students(sId,sName,sAge) <span class="hljs-keyword">values</span>(<span class="hljs-string">'S002'</span>,<span class="hljs-string">'Tom'</span>,<span class="hljs-number">25</span>);</span>
<span class="hljs-operator"><span class="hljs-keyword">insert</span> <span class="hljs-keyword">into</span> students(sId,sName,sAge) <span class="hljs-keyword">values</span>(<span class="hljs-string">'S003'</span>,<span class="hljs-string">'张三'</span>,<span class="hljs-number">43</span>);</span>
<span class="hljs-operator"><span class="hljs-keyword">insert</span> <span class="hljs-keyword">into</span> students(sId,sName,sAge) <span class="hljs-keyword">values</span>(<span class="hljs-string">'S004'</span>,<span class="hljs-string">'李四'</span>,<span class="hljs-number">55</span>);</span></code></pre>

<p>准备好必要的jar包。</p>

<h3 id="接下来配置好hibernatecfgxml文件">接下来配置好hibernate.cfg.xml文件:</h3>

<p>前面有的，就不重复了，配个图好了。 <br>
<img src="http://img.blog.csdn.net/20160828021537041" alt="" title=""></p>



<h3 id="pojo类">POJO类:</h3>



<pre class="prettyprint"><code class=" hljs java"><span class="hljs-keyword">package</span> cn.hncu.domain;

<span class="hljs-keyword">public</span> <span class="hljs-class"><span class="hljs-keyword">class</span> <span class="hljs-title">Student</span> {</span>
    <span class="hljs-keyword">private</span> String sId;
    <span class="hljs-keyword">private</span> String sName;
    <span class="hljs-keyword">private</span> Integer sAge;

    <span class="hljs-keyword">public</span> <span class="hljs-title">Student</span>() {
        <span class="hljs-keyword">super</span>();
    }
    <span class="hljs-keyword">public</span> String <span class="hljs-title">getsId</span>() {
        <span class="hljs-keyword">return</span> sId;
    }
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">setsId</span>(String sId) {
        <span class="hljs-keyword">this</span>.sId = sId;
    }
    <span class="hljs-keyword">public</span> String <span class="hljs-title">getsName</span>() {
        <span class="hljs-keyword">return</span> sName;
    }
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">setsName</span>(String sName) {
        <span class="hljs-keyword">this</span>.sName = sName;
    }
    <span class="hljs-keyword">public</span> Integer <span class="hljs-title">getsAge</span>() {
        <span class="hljs-keyword">return</span> sAge;
    }
    <span class="hljs-keyword">public</span> <span class="hljs-keyword">void</span> <span class="hljs-title">setsAge</span>(Integer sAge) {
        <span class="hljs-keyword">this</span>.sAge = sAge;
    }
    <span class="hljs-annotation">@Override</span>
    <span class="hljs-keyword">public</span> String <span class="hljs-title">toString</span>() {
        <span class="hljs-keyword">return</span> <span class="hljs-string">"Student [sId="</span> + sId + <span class="hljs-string">", sName="</span> + sName + <span class="hljs-string">", sAge="</span> + sAge
                + <span class="hljs-string">"]"</span>;
    }
}
</code></pre>



<h3 id="cnhncudomainstudenthbmxml映射文件">cn/hncu/domain/Student.hbm.xml映射文件</h3>



<pre class="prettyprint"><code class=" hljs xml"><span class="hljs-pi">&lt;?xml version="1.0" encoding="utf-8"?&gt;</span>
<span class="hljs-doctype">&lt;!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd"&gt;</span>

<span class="hljs-tag">&lt;<span class="hljs-title">hibernate-mapping</span> <span class="hljs-attribute">package</span>=<span class="hljs-value">"cn.hncu.domain"</span>&gt;</span>

    <span class="hljs-comment">&lt;!--name指的是POJO类的类名，table是数据库的表名，catalog是数据库的名称  --&gt;</span>
    <span class="hljs-tag">&lt;<span class="hljs-title">class</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"Student"</span> <span class="hljs-attribute">table</span>=<span class="hljs-value">"students"</span> <span class="hljs-attribute">catalog</span>=<span class="hljs-value">"hib"</span>&gt;</span>
        <span class="hljs-comment">&lt;!--id表示此字段为数据库的主键，这里也可以用property来写，name为Student类的成员变量名，type为类型的包全名  --&gt;</span>
        <span class="hljs-tag">&lt;<span class="hljs-title">id</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"sId"</span> <span class="hljs-attribute">type</span>=<span class="hljs-value">"java.lang.String"</span>&gt;</span>
            <span class="hljs-comment">&lt;!--column表示映射的数据库的字段，name表示数据库中字段名，length表示varchar/char型的长度  --&gt;</span>
            <span class="hljs-tag">&lt;<span class="hljs-title">column</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"sId"</span> <span class="hljs-attribute">length</span>=<span class="hljs-value">"8"</span>&gt;</span><span class="hljs-tag">&lt;/<span class="hljs-title">column</span>&gt;</span>
        <span class="hljs-tag">&lt;/<span class="hljs-title">id</span>&gt;</span>
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"sName"</span> <span class="hljs-attribute">type</span>=<span class="hljs-value">"java.lang.String"</span>&gt;</span>
            <span class="hljs-tag">&lt;<span class="hljs-title">column</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"sName"</span> <span class="hljs-attribute">length</span>=<span class="hljs-value">"40"</span> /&gt;</span>
        <span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>
        <span class="hljs-tag">&lt;<span class="hljs-title">property</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"sAge"</span> <span class="hljs-attribute">type</span>=<span class="hljs-value">"java.lang.Integer"</span>&gt;</span>
            <span class="hljs-tag">&lt;<span class="hljs-title">column</span> <span class="hljs-attribute">name</span>=<span class="hljs-value">"sAge"</span> /&gt;</span>
        <span class="hljs-tag">&lt;/<span class="hljs-title">property</span>&gt;</span>
    <span class="hljs-tag">&lt;/<span class="hljs-title">class</span>&gt;</span>

<span class="hljs-tag">&lt;/<span class="hljs-title">hibernate-mapping</span>&gt;</span></code></pre>



<h3 id="hibernatesessionfactory">HibernateSessionFactory</h3>



<pre class="prettyprint"><code class=" hljs java"><span class="hljs-keyword">package</span> cn.hncu.hib;

<span class="hljs-keyword">import</span> org.hibernate.HibernateException;
<span class="hljs-keyword">import</span> org.hibernate.Session;
<span class="hljs-keyword">import</span> org.hibernate.SessionFactory;
<span class="hljs-keyword">import</span> org.hibernate.cfg.Configuration;

<span class="hljs-keyword">public</span> <span class="hljs-class"><span class="hljs-keyword">class</span> <span class="hljs-title">HibernateSessionFactory</span> {</span>
   <span class="hljs-keyword">private</span> <span class="hljs-keyword">static</span> String configFile = <span class="hljs-string">"/hibernate.cfg.xml"</span>;
   <span class="hljs-keyword">private</span> <span class="hljs-keyword">static</span> Configuration config = <span class="hljs-keyword">new</span> Configuration();
   <span class="hljs-keyword">private</span> <span class="hljs-keyword">static</span> SessionFactory sessionFactory =<span class="hljs-keyword">null</span>;

   <span class="hljs-keyword">private</span> <span class="hljs-keyword">static</span> <span class="hljs-keyword">final</span> ThreadLocal&lt;Session&gt; t = <span class="hljs-keyword">new</span> ThreadLocal&lt;Session&gt;();

   <span class="hljs-keyword">static</span>{
       <span class="hljs-keyword">try</span> {
           <span class="hljs-comment">/*创建一个配置对象，读取配置文件*/</span>
           config.configure(configFile);
           <span class="hljs-comment">/*通过配置对象产生一个会话工厂类*/</span>
           sessionFactory = config.buildSessionFactory();
        } <span class="hljs-keyword">catch</span> (HibernateException e) {
            e.printStackTrace();
        }
   }

   <span class="hljs-keyword">public</span> <span class="hljs-keyword">static</span> Session <span class="hljs-title">getSession</span>() <span class="hljs-keyword">throws</span> HibernateException{
       Session session = t.get();
       <span class="hljs-keyword">if</span>(session == <span class="hljs-keyword">null</span> || !session.isOpen()){
           <span class="hljs-keyword">if</span>(sessionFactory==<span class="hljs-keyword">null</span>){
               rebuildSessionFactory();
           }
           <span class="hljs-comment">/*通过会话工厂类产生一个会话实例*/</span>
           session = (sessionFactory!=<span class="hljs-keyword">null</span>) ? sessionFactory.openSession() : <span class="hljs-keyword">null</span>;
           t.set(session);
       }
       <span class="hljs-keyword">return</span> session;
   }

   <span class="hljs-keyword">private</span> <span class="hljs-keyword">static</span> <span class="hljs-keyword">void</span> <span class="hljs-title">rebuildSessionFactory</span>() {
       <span class="hljs-keyword">try</span> {
           config.configure(configFile);
           sessionFactory = config.buildSessionFactory();
        } <span class="hljs-keyword">catch</span> (HibernateException e) {
            e.printStackTrace();
        }
   }

   <span class="hljs-comment">//关闭与数据库的会话</span>
   <span class="hljs-keyword">public</span> <span class="hljs-keyword">static</span> <span class="hljs-keyword">void</span> <span class="hljs-title">closeSession</span>() <span class="hljs-keyword">throws</span> HibernateException{
       Session session = t.get();
       t.set(<span class="hljs-keyword">null</span>);
       <span class="hljs-keyword">if</span>(session!=<span class="hljs-keyword">null</span>){
           session.close();
       }
   }
}</code></pre>



<h3 id="testhib">TestHib</h3>



<pre class="prettyprint"><code class=" hljs avrasm">package cn<span class="hljs-preprocessor">.hncu</span><span class="hljs-comment">;</span>

import java<span class="hljs-preprocessor">.util</span><span class="hljs-preprocessor">.List</span><span class="hljs-comment">;</span>

import org<span class="hljs-preprocessor">.hibernate</span><span class="hljs-preprocessor">.Query</span><span class="hljs-comment">;</span>
import org<span class="hljs-preprocessor">.hibernate</span><span class="hljs-preprocessor">.Session</span><span class="hljs-comment">;</span>
import org<span class="hljs-preprocessor">.hibernate</span><span class="hljs-preprocessor">.SessionFactory</span><span class="hljs-comment">;</span>
import org<span class="hljs-preprocessor">.hibernate</span><span class="hljs-preprocessor">.cfg</span><span class="hljs-preprocessor">.Configuration</span><span class="hljs-comment">;</span>

import cn<span class="hljs-preprocessor">.hncu</span><span class="hljs-preprocessor">.domain</span><span class="hljs-preprocessor">.Student</span><span class="hljs-comment">;</span>
import cn<span class="hljs-preprocessor">.hncu</span><span class="hljs-preprocessor">.hib</span><span class="hljs-preprocessor">.HibernateSessionFactory</span><span class="hljs-comment">;</span>

public class TestHib {

    public static void main(String[] args) {
        <span class="hljs-comment">/*创建一个配置对象，读取配置文件*/</span>
        String configfile=<span class="hljs-string">"/hibernate.cfg.xml"</span><span class="hljs-comment">;</span>
        Configuration config=new Configuration()<span class="hljs-comment">;</span>
        config<span class="hljs-preprocessor">.configure</span>(configfile)<span class="hljs-comment">;</span>
        <span class="hljs-comment">/*通过配置对象产生一个会话工厂类*/</span>
        SessionFactory sessionfactory=config<span class="hljs-preprocessor">.buildSessionFactory</span>()<span class="hljs-comment">;</span>
        <span class="hljs-comment">/*通过会话工厂类产生一个会话实例*/</span>
        Session session=sessionfactory<span class="hljs-preprocessor">.openSession</span>()<span class="hljs-comment">;</span>
        <span class="hljs-comment">/*通过会话产生一个查询对象Query*/</span>
        Query query=session<span class="hljs-preprocessor">.createQuery</span>(<span class="hljs-string">"from Student"</span>)<span class="hljs-comment">;</span>
        <span class="hljs-comment">/*进行查询返回一个集合List*/</span>
        List&lt;Student&gt; studs=query<span class="hljs-preprocessor">.list</span>()<span class="hljs-comment">;</span>
        for(Student s:studs){
           System<span class="hljs-preprocessor">.out</span><span class="hljs-preprocessor">.println</span>(s)<span class="hljs-comment">;</span>
        }

        session = HibernateSessionFactory<span class="hljs-preprocessor">.getSession</span>()<span class="hljs-comment">;</span>
        query = session<span class="hljs-preprocessor">.createQuery</span>(<span class="hljs-string">"from Student"</span>)<span class="hljs-comment">;</span>
        List&lt;Student&gt; students = query<span class="hljs-preprocessor">.list</span>()<span class="hljs-comment">;</span>
        for(Student s: students){
            System<span class="hljs-preprocessor">.out</span><span class="hljs-preprocessor">.println</span>(s)<span class="hljs-comment">;</span>
        }
    }
}
</code></pre>



<h3 id="测试结果">测试结果:</h3>

<p><img src="http://img.blog.csdn.net/20160828022344667" alt="" title=""></p>

<blockquote cite="陈浩翔">
<p>转载请注明出处：<a href="http://blog.csdn.net/qq_26525215"><font color="green">http://blog.csdn.net/qq_26525215</font></a><br><br>
本文源自<strong>【<a href="http://blog.csdn.net/qq_26525215" target="_blank">大学之旅_谙忆的博客</a>】</strong></p>
</blockquote></div></body>
</html>