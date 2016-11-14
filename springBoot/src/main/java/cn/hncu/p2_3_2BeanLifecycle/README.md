
#分析

在我们实际开发的时候，经常会遇到在Bean使用之前或者之后做些必要的操作，Spring对Bean的生命周期的操作提供了支持。

有如下2种方式:
1、Java配置方式:使用@Bean的initMethod和destroyMethod(相当于xml配置的init-method和destroy-method)

2、注解方式:
利用JSR-250的@PostConstruct和@PreDestroy

友情提示:
进行本示例的演示，需要先配置好Maven和Spring哦、
见:<a href="http://blog.csdn.net/qq_26525215/article/details/53010442" target='_blank'>【Spring】基于IntelliJ IDEA搭建Maven</a>



#示例

##增加JSR250支持

```
<dependency>
       <groupId>javax.annotation</groupId>
       <artifactId>jsr250-api</artifactId>
       <version>1.0</version>
</dependency>
```

##使用@Bean形式的Bean

```
package cn.hncu.p2_3_2BeanLifecycle;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/14.
 * Time: 上午 11:19.
 * Explain:使用@Bean形式的Bean
 */
public class BeanWayService {
    public BeanWayService() {
        super();
        System.out.println("初始化构造函数-BeanWayService:"+this.getClass());
    }

    public void init(){
        System.out.println("BeanWayService-init方法");
    }

    public void destroy(){
        System.out.println("BeanWayService-destroy方法");
    }
}

```

##使用JSR250形式的Bean

```
package cn.hncu.p2_3_2BeanLifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/14.
 * Time: 上午 11:23.
 * Explain:使用JSR250形式的Bean
 */
public class JSR250WayService {
    public JSR250WayService() {
        super();
        System.out.println("初始化构造函数-JSR250WayService:"+this.getClass());
    }

    @PostConstruct//@PostConstruct这个注解表明该方法在构造函数执行之后执行
    public void init(){
        System.out.println("jsr250-init方法");
    }

    @PreDestroy//这个注解表明该方法在Bean销毁之前执行
    public void destroy(){
        System.out.println("jsr250-destroy方法");
    }
}

```

##配置类

```
package cn.hncu.p2_3_2BeanLifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/14.
 * Time: 上午 11:29.
 * Explain:配置类
 */
@Configuration
@ComponentScan("cn.hncu.p2_3_2BeanLifecycle")
public class PrePostConfig {

    //为BeanWayService准备的配置方法
    //initMethod和destroyMethod指定BeanWayService类的init和destroy方法在构造之后、Bean销毁之前执行
    @Bean(initMethod = "init",destroyMethod = "destroy")
    BeanWayService beanWayService(){
        return new BeanWayService();
    }

    //为JSR250WayService准备的配置方法
    @Bean
    JSR250WayService jsr250WayService(){
        return new JSR250WayService();
    }


}

```

##运行类

```
package cn.hncu.p2_3_2BeanLifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/14.
 * Time: 上午 11:33.
 * Explain:运行类
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PrePostConfig.class);

        BeanWayService beanWayService = context.getBean(BeanWayService.class);
        JSR250WayService jsr250WayService = context.getBean(JSR250WayService.class);

        context.close();
    }
}

```

#运行结果

![](http://img.blog.csdn.net/20161114192851789)

