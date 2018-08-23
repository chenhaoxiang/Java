进行本示例的演示，需要先配置好Maven和Spring哦、
见:
<a href="http://blog.csdn.net/qq_26525215/article/details/53010442" target='_blank'>【Spring】基于IntelliJ IDEA搭建Maven</a>

#分析

测试是开发工作中不可缺少的部分。

单元测试只针对当前开发的类和方法进行测试，可以简单通过模拟依赖来实现，对运行环境没有依赖；

但是仅仅进行单元测试是不够的，它只能验证当前类或当前方法能否正常工作，而我们想要知道系统的各个部分组合在一起是否能正常工作，这就是集成测试存在的意义！

集成测试一般需要来自不同层的不同对象的交互，如数据库、网络连接、Ioc容器等。

其实我们也经常通过运行程序，然后通过自己操作来完成类似于集成测试的流程。集成测试为我们提供了一种无须部署或运行程序来完成验证系统各部分是否能正常协同工作的能力。

Spring通过Spring TestContext Framework 对集成测试提供顶级支持。

它不依赖于特定的测试框架，即可使用Junit，也可使用TestNG。
在下面的示例中，使用的是Junit。

基于Maven构建的项目结构默认有关测试的目录是：src/test/java（测试代码）、src/test/resources（测试资源），区别于src/main/java（项目源码）、src/main/resources（项目资源）。

Spring提供了一个SpringJunitClassRunner类，它提供了Spring TestContext Framework的功能。

通过@ContextConfiguration来配置Application Context，通过@ActiveProfiles确定活动的profile。

在使用了Spring测试后，之前我的博客写的运行类，也就是运行部分都可以使用Spring测试来检测功能能否正常运作。

集成测试涉及程序中的各个分层，本节只对简单配置的Application Context和在测试中注入Bean做演示。

#示例

##准备
首先，我们需要准备一下，增加Spring测试的依赖包到Maven。
也就是加入到pom.xml中去。

```
<!--Spring test 支持 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>4.2.3.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>
```

##业务代码

此源码在src/main/java下

```
package cn.hncu.p3.p7_fortest;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/12/9.
 * Time: 下午 9:21.
 * Explain:业务代码
 */
public class TestBean {
    private String content;

    public TestBean(String content) {
        super();
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

```

##配置类

此源码在src/main/java下

```
package cn.hncu.p3.p7_fortest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/12/9.
 * Time: 下午 9:23.
 * Explain:配置类
 */
@Configuration
public class TestConfig {
    @Bean
    @Profile("dev")
    public TestBean devTestBean(){
        return new TestBean("development profile");
    }

    @Bean
    @Profile("prod")
    public TestBean prodTestBean(){
        return new TestBean("production profile");
    }
}

```

##测试

注意！此源码在src/test/java下

```
package cn.hncu.p3.p7_fortest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/12/9.
 * Time: 下午 9:27.
 * Explain:测试类---源码在src/test/java下
 */
@RunWith(SpringJUnit4ClassRunner.class)//在SpringJUnit4ClassRunner在Junit环境下提供Spring TextContext Framework的功能
@ContextConfiguration(classes = {TestConfig.class})//@ContextConfiguration用来加载配置ApplicationContext，其中classes属性用来加载配置类
@ActiveProfiles("prod")
public class DemoBeanIntegrationTests {
    @Autowired//可以使用普通的@Autowired注入Bean
    private TestBean testBean;

    @Test//测试代码，通过Junit的Assert来校验结果是否和预期的一样
    public void prodBeanShouldInject(){
        String expected = "production profile";
        String actual = testBean.getContent();
        System.out.println(actual);
        Assert.assertEquals(expected,actual);//用来查看对象中存的值是否是期待的值，与字符串比较中使用的equals()方法类似；
    }
}

```

#运行结果

![](http://img.blog.csdn.net/20161210113005258)

我们现在改一下测试类

将此处的
![](http://img.blog.csdn.net/20161210113138107)
@ActiveProfile("prod")改为@ActiveProfile("dev")
只改此处。

看运行结果：

![](http://img.blog.csdn.net/20161210113319217)

这是测试不能通过的结果！

会有红色警示！
