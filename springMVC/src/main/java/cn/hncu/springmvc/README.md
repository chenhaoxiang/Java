#分析
Spring MVC提供了一个DispatcherServlet来开发Web应用。

在Servlet 2.5 及以下的时候只要在web.xml下配置`<servlet>`元素即可。
在下面的示例中，使用的是Servlet3.0+无web.xml的配置方式，在Spring MVC里实现WebApplicationInitialzer接口实现等同于web.xml的配置。

下面实例在IDEA中基于Maven搭建零配置的Spring MVC原型项目。              

#示例

##构建Maven项目

pom.xml内容：

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>cn.chenhaoxiang</groupId>
    <artifactId>highlight_springmvc4</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>war</packaging>

    <properties>
        <!--Generic properties -->
        <java.version>1.7</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>

        <!-- Web -->
        <jsp.version>2.2</jsp.version>
        <jstl.version>1.2</jstl.version>
        <servlet.version>3.1.0</servlet.version>

        <!-- Spring -->
        <spring-framework.version>4.2.3.RELEASE</spring-framework.version>

        <!--Logging -->
        <logback.version>1.0.13</logback.version>
        <slf4j.version>1.7.7</slf4j.version>
        <log4j.version>1.2.17</log4j.version>

    </properties>

    <dependencies>
        <dependency>
            <groupId>javax</groupId>
            <artifactId>javaee-web-api</artifactId>
            <version>7.0</version>
            <scope>provided</scope>
        </dependency>

        <!-- Spring MVC -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>${spring-framework.version}</version>
        </dependency>

        <!-- 其他Web依赖 -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>${jstl.version}</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>${servlet.version}</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>jsp-api</artifactId>
            <version>${jsp.version}</version>
            <scope>provided</scope>
        </dependency>

        <!-- Spring and Transaction -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
            <version>${spring-framework.version}</version>
        </dependency>

        <!-- 使用SLF4J和LogBack作为日志 -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>${slf4j.version}</version>
        </dependency>
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>${log4j.version}</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>jcl-over-slf4j</artifactId>
            <version>${slf4j.version}</version>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>${logback.version}</version>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-core</artifactId>
            <version>${logback.version}</version>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-access</artifactId>
            <version>${logback.version}</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>2.3.2</version>
                <configuration>
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-war-plugin</artifactId>
                <version>2.3</version>
                <configuration>
                    <failOnMissingWebXml>false</failOnMissingWebXml>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```

##日志配置

在src/main/resources目录下，新建logback.xml用来配置日志，内容如下：

```
<?xml version="1.0" encoding="UTF-8"?>
<!-- 可以这样描述配置文件的基本结构：以<configuration>开头，
后面有零个或多个<appender>元素，有零个或多个<logger>元素，有最多一个<root>元素 -->
<configuration debug="false" scan="true" scanPeriod="1 seconds">
    <!-- debug : 默认为false ，设置为true时，将打印出logback内部日志信息，实时查看logback运行状态。
    scan : 配置文件如果发生改变，将会重新加载，默认值为true；
    scanPeriod : 检测配置文件是否有修改的时间间隔，如果没有给出时间单位，默认单位时毫秒，
    当scan为true时，这个属性生效，默认时间间隔为1min。    -->

    <contexListener class="ch.qos.logback.classic.jul.LevelChangePropagator">
        <resetJUL>true</resetJUL>
    </contexListener>

    <jmxConfigurator/>

    <appender name="console" class="ch.qos.logback.core.ConsoleAppender">
        <!-- <appender>  是  <configuration> 的子节点，是负责写日志的组件。
         appender 有两个必要属性 name ，class 。name指定appender 的名称， class 指定appender的全限定名
         class 包括 ：ch.qos.logback.core.ConsoleAppender / ch.qos.logback.core.FileAppender/ ch.qos.logback.core.RollingFileAppender -->
        <encoder>
            <!--ch.qos.logback.core.ConsoleAppender 把日志添加到控制台，有如下节点： <encoder> : 对日志进行格式化。
            <target> : 字符串System.out 或者 System.err, 默认 System.out;
            ch.qos.logback.core.FileAppender 把日志添加到文件
             ch.qos.logback.core.RollingFileAppender[常用] 滚动纪录文件，先将日志记录到指定文件，当符合某种条件时，将日志记录到其他文件-->
            <pattern>logbak: %d{HH:mm:ss.SSS} %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <logger name="org.springframework.web" level="DEBUG"></logger>
    <!--logger 是 <configuration> 的子节点 来设置某一个包或者具体的某一个类的日志打印级别，以及指定<appender>
      loger 仅有一个name属性，两个可选属性 level／addtivity
      name ： 用来指定受此loger约束的某一个包或者具体的某一个类
      level：用来设置打印级别，大小写无关-->

    <!-- 元素配置根 logger。该元素有一个 level 属性。没有 name 属性，因为已经被命名 为“root”-->
    <!-- 默认根节点是INFO级别的日志 -->
    <!-- root：logger的根节点，就这一个，默认名称就是root
        level：日志级别
        appender-ref：确定使用哪个appender
         -->
    <root level="info">
        <appender-ref ref="console"></appender-ref>
    </root>

</configuration>
```
将org.springframwork.web包下的类的日志级别设置为DEBUG，我们开发Spring MVC经常出现和参数类型相关的4XX错误，设置此项我们会看到更详细的错误信息。

##演示页面

在这里有一个注意的地方，页面的创建位置。
在src/main/resources下建立views目录，并在此目录下新建index.jsp。
内容如下：

```
<%--
  Created by IntelliJ IDEA.
  User: 陈浩翔
  Date: 2016/12/18
  Time: 下午 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MVC Title</title>
</head>
<body>
    <pre>Welcome to Spring MVC</pre>
    <br/>
    欢迎！
</body>
</html>

```

这里的页面不放在Maven标准的src/main/webapp/WEB-INF下，在这里这么放的原因是，Spring Boot的页面是放置在src/main/resources下。

##Spring MVC配置类

这个就是一个简单的Spring配置类。
在这里配置了一个JSP的ViewResolver，用来映射路径和实际页面的位置，其中，@EnableWebMvc注解会开启一些默认配置，如一些ViewResolver或者MessageConverter等

```
package cn.hncu.springmvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/12/19.
 * Time: 下午 3:39.
 * Explain:配置类
 */
@Configuration
@EnableWebMvc
@ComponentScan("cn.hncu.springmvc")
public class MyMvcConfig {
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/");//映射路径-运行时代码会将我们的页面自动编译到/WEB-INF/classes/views/下
        viewResolver.setSuffix(".jsp");//实际页面后缀
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }
}

```
Spring MVC的ViewResolver，这个是Spring MVC视图(JSP下就是html)渲染的核心机制；Spring MVC里有一个接口叫做ViewResolver(我们的ViewResolver都实现该接口)，实现这个接口要重写resolverViewName()，这个方法的返回值是接口View，而View的职责就是使用model、request、response对象，并将渲染的视图(不一定是html，可能是json、xml、pdf)返回给浏览器。

可能会对映射路径前缀配置为/WEB-INF/classes/views/有点奇怪，这不是我们放页面的路径啊。
这是因为看到的页面效果是运行时而不是开发时的代码，运行时代码会将我们的页面自动编译到/WEB-INF/classes/views/下、
如图：
![](http://img.blog.csdn.net/20161219172535752?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMjY1MjUyMTU=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
这个是编译后的路径！

##Web配置类
这个类就是为了代替web.xml的位置的。

```
package cn.hncu.springmvc;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/12/19.
 * Time: 下午 3:44.
 * Explain:Web配置
 */
public class WebInitializer implements WebApplicationInitializer {
    //WebApplicationInitializer是spring提供用来配置Servlet3.0+配置的接口，从而实现了替代web.xml的位置。
    //实现此接口将会自动被SpringServletContainerInitializer（用来启动Servlet3.0容器）获取到

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(MyMvcConfig.class);
        context.setServletContext(servletContext);//新建WebApplicationContext,注册配置类，并将其和当前servletContext关联

        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher",new DispatcherServlet(context));
        //注册Spring MVC的DispatcherServlet
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }
}

```

##简单的控制器

```
package cn.hncu.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/12/19.
 * Time: 下午 3:55.
 * Explain:简单控制器
 */
@Controller //利用@Controller注解声明是一个控制器
public class HelloController {
    @RequestMapping("/index")//利用@RequestMapping配置URL和方法之间的映射
    public String hello(){
        return "index";//通过上面ViewResolver的Bean配置，返回值为index，说明我们的页面放置的路径是：/WEB-INF/classes/views/index.jsp
    }
}

```

在这里，我们通过@RequestMapping配置URL和方法之间的映射，来访问实际的页面！


#运行结果

最后，将程序部署到Tomcat中，启动Tomcat,访问：
http://localhost:8080/springMVC/index

![](http://img.blog.csdn.net/20161219172916883)