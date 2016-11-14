
Spring框架本身有四大原则:
1、使用POJO进行轻量级和最小侵入式开发
2、通过依赖注入和基于接口编程实现松耦合
3、通过AOP和默认习惯进行声明式编程
4、使用AOP和模板减少模式化代码

在Spring环境中，控制翻转（IoC）-Inversion of Control和依赖注入（DI）-dependency injection是等同的概念。

控制翻转是通过依赖注入实现的。所谓依赖注入指的是容器负责创建对象和维护对象间的依赖关系，而不是通过对象本身负责自己的创建和解决自己的依赖。

依赖注入的主要目的是为了解耦，体现了一种"组合"的理念。

可以这样理解，当我们希望某个类具备某项功能时，可以选择继承一个具有此功能的类，也可以选择组合另外一个具有此功能的类。那么显然，我们选择组合。因为组合另外一个类会使得耦合度大大降低。

组合:组合关系就是整体与部分的关系，部分属于整体，整体不存在，部分一定不存在，然而部分不存在整体是可以存在的，说的更明确一些就是部分必须创生于整体创生之后，而销毁于整体销毁之前。部分在这个生命期内可以被其它对象关联甚至聚合，但有一点必须注意，一旦部分所属于的整体销毁了，那么与之关联的对象中的引用就会成为空引用，这一点可以利用程序来保障。


Spring IoC容器（ApplicationContext）负责创建Bean，并通过容器将功能类Bean注入到你需要的Bean中。
Spring提供使用xml，注解，Java配置，groovy配置实现Bean的创建和注入。
这些配置方式，都被称为配置元数据。
元数据:即描述数据的数据。元数据本身不具备任何可执行的能力，只能通过外界代码来对这些元数据行解析后进行一些有意义操作。
Spring容器解析这些配置元数据进行Bean初始化、配置和管理依赖。

声明Bean的注解
	@Component组件，没有明确的角色。
	@Service在业务逻辑层(service层)使用。
	@Repository在数据访问层(dao层)使用。
	@Controller在展现层(MVC)
	
注入Bean的注解，一般情况下通用
	@Autowired：Spring提供的注解。
	@Inject：JSR-330提供的注解。
	@Resource：JSR-250提供的注解。
@Autowired,@Inject,@Resource都可注解在set方法上或者属性上。

演示实例:
演示基于注解的Bean的初始化和依赖注入。

首先需要先安装好Maven，搭建好Spring，
我在这篇博客中有介绍:
http://blog.csdn.net/qq_26525215/article/details/53010442

编写功能类Bean：
```
package cn.hncu.p1_3_1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/3.
 * Time: 上午 9:03.
 */
@Service
//使用@Service注解声明当前FunctionService类是Spring管理的一个Bean。其中，使用@Component,@Service,@Repository和
//@Controller是等效的。
public class FunctionService {
    public String sayHello(String word){
        return "Hello "+word +" !";
    }
}
```


使用功能类Bean：
```
package cn.hncu.p1_3_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/3.
 * Time: 上午 9:04.
 */
@Service
//使用@Service注解声明当前UseFunctionService类是Spring管理的一个Bean
public class UseFunctionService {
    @Autowired
    //使用@Autowired将FunctionService的尸体Bean注入到UseFunctionService中，
    //让UseFunctionService具备FunctionService的功能，此处使用@Inject或者@Resource注解是等效的。
    FunctionService functionService;
    public String sayHello(String word){
        return functionService.sayHello(word);
    }
}

```


配置类:

```
package cn.hncu.p1_3_1;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/3.
 * Time: 上午 9:08.
 */
@Configuration
//@Configuration声明当前类是配置类。
@ComponentScan("cn.hncu.p1_3_1")
//使用@ComponentScan，自动扫描包名下所有使用@Service，@Component，@Repository和@Controller的类，并注册为Bean
public class DiConfig {
}

```

运行:

```
package cn.hncu.p1_3_1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/3.
 * Time: 上午 9:09.
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DiConfig.class);
        //使用AnnotationConfigApplicationContext作为Spring容器，接受输入一个配置类作为参数
        UseFunctionService useFunctionService = context.getBean(UseFunctionService.class);
        //获得声明配置的UseFunctionService的Bean
        System.out.println(useFunctionService.sayHello("张三"));
        context.close();
}
}

```

输出结果:
![](http://img.blog.csdn.net/20161104213807840)

