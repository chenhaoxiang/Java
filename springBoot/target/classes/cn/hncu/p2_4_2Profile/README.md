#分析
对于Profile先做一个简单的介绍:
单讲profile就是一组配置，不同profile提供不同组合的配置，程序运行时可以选择使用哪些profile来适应环境。

也就是Profile为在不同环境下使用不同的配置提供了支持(开发环境下的配置和生产环境下的配置肯定是不同的，例如:数据库的配置)


Spring 为我们提供了大量的激活 profile 的方法，可以通过代码来激活，也可以通过系统环境变量、JVM参数、servlet上下文参数来定义 spring.profiles.active 参数激活 profile,下面说下3种方法:
1、通过设定Environment的ActiveProfiles来设定当前context需要使用的配置环境。在开发中使用@profile注解类或者方法，达到在不同情况下选择实例化不同的Bean。
2、通过设定jvm的spring.profile.active参数来设置配置环境。
3、Web项目设置在Service的context parameter中。


进行本示例的演示，需要先配置好Maven和Spring哦、
见:
<a href="http://blog.csdn.net/qq_26525215/article/details/53010442" target='_blank'>【Spring】基于IntelliJ IDEA搭建Maven</a>

在这里的示例只演示第一种方式的示例哦。

#示例

##示例Bean

```
package cn.hncu.p2_4_2Profile;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/14.
 * Time: 下午 8:37.
 * Explain:示例Bean
 */
public class DemoBean {
    public String content;

    public DemoBean(String content) {
        super();
        this.content = content;
    }
    public String getContent(){
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}

```

##Profile配置

```
package cn.hncu.p2_4_2Profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/14.
 * Time: 下午 8:41.
 * Explain:Profile配置
 */
@Configuration
public class ProfileConfig {

    @Bean
    @Profile("dev")//Profile为dev时实例化devDemoBean
    public DemoBean devDemoBean(){
        return new DemoBean("from development profile");
    }

    @Bean
    @Profile("prod")//Profile为prod时实例化prodDemoBean
    public DemoBean prodDemoBean(){
        return new DemoBean("from production profile");
    }
}

```

##运行类

```
package cn.hncu.p2_4_2Profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/14.
 * Time: 下午 8:51.
 * Explain:运行类
 */
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("prod");//先将活动的Proofile设置为prod
        context.register(ProfileConfig.class);//后置注册Bean的配置类，不然会报Bean未定义的错误
        context.refresh();//刷新容器

        DemoBean demoBean = context.getBean(DemoBean.class);

        System.out.println(demoBean.getContent());

        context.close();
    }
}

```
##运行结果

![](http://img.blog.csdn.net/20161114214508240)

现在来对Main类做一下改动，将
context.getEnvironment().setActiveProfiles("prod")
修改为
context.getEnvironment().setActiveProfiles("dev")


结果如下:

![](http://img.blog.csdn.net/20161114214722365)
