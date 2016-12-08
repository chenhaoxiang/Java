#分析

所谓的元注解：其实就是可以注解到别的注解上的注解。
而被注解的注解我们就称之为组合注解。（仔细理解，可能有点绕）
组合注解具备元注解的功能！

Spring的很多注解都可以作为元注解，而且Spring本身已经有很多组合注解。

比如@Configuration就是一个组合@Component注解，表明这个类其实也是一个Bean。
@Configuration的源码:
```
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springframework.context.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.stereotype.Component;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Configuration {
    String value() default "";
}

```

有的时候，我们可能大量同时使用到几个注解到同一个类上，这个时候，我们就可以考虑将这几个注解到别的注解上。

比如下面的示例就是将@Configuration和@ComponentScan注解到一个注解上！

这样，我们就可以用一个注解来表示这两个注解。

#示例

##组合注解

```
package cn.hncu.p3.p5_annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/12/8.
 * Time: 下午 4:00.
 * Explain:组合注解
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration//组合@Configuration元注解  bean注解
@ComponentScan//组合@ComponentScan元注解  自动扫描对应value（package路径）值下面的所有bean
public @interface WiselyConfiguration {
    String[] value() default {};//覆盖value参数
    //就是覆盖@ComponentScan注解中的value参数----必须要写，否则组合注解中放入value值时会报错
}

```
解释一下@Documented：
表明这个注解应该被 javadoc工具记录. 默认情况下,javadoc是不包括注解的. 但如果声明注解时指定了 @Documented,则它会被 javadoc 之类的工具处理, 所以注解类型信息也会被包括在生成的文档中.

##服务Bean

```
package cn.hncu.p3.p5_annotation;

import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/12/8.
 * Time: 下午 8:17.
 * Explain:服务Bean
 */
@Service
public class DemoService {
    public void outputResult(){
        System.out.println("从组合注解配置照样获得的bean");
    }
}

```

##配置类

现在就只需要我们自定义的那个注解就可以代表那两个注解了。

```
package cn.hncu.p3.p5_annotation;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/12/8.
 * Time: 下午 8:19.
 * Explain:配置类--组合注解
 */
@WiselyConfiguration("cn.hncu.p3.p5_annotation")
//自定义注解，扫描的所有的bean来源于value值所对应的包路径下
//使用@WiselyConfiguration组合注解替代@Configuration和@ComponentScan
public class DemoConfig {
}

```

##运行类

```
package cn.hncu.p3.p5_annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/12/8.
 * Time: 下午 8:21.
 * Explain:运行类
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
        DemoService demoService = context.getBean(DemoService.class);
        demoService.outputResult();
        context.close();
    }
}

```

#运行结果

![](http://img.blog.csdn.net/20161208204519300)

