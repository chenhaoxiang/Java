#分析

在前面的一篇博客中：<a href="http://blog.csdn.net/qq_26525215/article/details/53164481" target='_blank'>【Spring】Spring常用配置-Profile </a>

通过profile，我们可以获得不同的profile，我们可以获得不同的Bean。Spring4提供了一个更通用的基于条件的Bean的创建，即使用@Condition注解。

@Condition根据满足某一个特定条件创建一个特定的Bean。
比如说，当某一个jar包在一个类路径下的时候，自动配置一个或多个Bean；或者只有某个Bean被创建才会创建另外一个Bean。

总的来说，就是根据特定条件来控制Bean的创建行为，这样我们可以利用这个特性来进行一些自动的配置。

下面这个示例将以不同的操作系统来作为条件，通过实现Condition接口，并重写其matches方法来构造判断条件。

若在Windows系统下运行程序，则输出列表命令为dir；若在Linux操作系统下运行程序，则输出列表命令为ls。

#示例

##先需要定义判断条件

###判定Windows的条件

```
package cn.hncu.p3.p4_conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/12/7.
 * Time: 下午 7:24.
 * Explain:判断Windows的条件
 */
public class WindowsCondition implements Condition{
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata){
        return context.getEnvironment().getProperty("os.name").contains("Windows");
    }
}

```

###判定Linux的条件

```
package cn.hncu.p3.p4_conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/12/7.
 * Time: 下午 7:28.
 * Explain:判定Linux的条件
 */
public class LinuxCondition implements Condition{
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return context.getEnvironment().getProperty("os.name").contains("Linux");
    }
}

```

##不同系统下Bean的类

首先需要一个接口，接下来不同的Bean都需要实现这个接口。

###接口

```
package cn.hncu.p3.p4_conditional;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/12/7.
 * Time: 下午 7:31.
 * Explain:接口-Bean需要实现的接口
 */
public interface ListService {
    public String showListCmd();
}

```
###Windows下所要创建的Bean的类

```
package cn.hncu.p3.p4_conditional;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/12/7.
 * Time: 下午 7:41.
 * Explain:Windows下所要创建的Bean的类
 */
public class WindowsListService implements ListService {
    @Override
    public String showListCmd() {
        return "dir";
    }
}

```

###Linux下所要创建的Bean的类

```
package cn.hncu.p3.p4_conditional;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/12/7.
 * Time: 下午 7:42.
 * Explain:Linux下所要创建的Bean的类
 */
public class LinuxListService implements ListService {
    @Override
    public String showListCmd() {
        return "ls";
    }
}

```

##配置类

```
package cn.hncu.p3.p4_conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/12/7.
 * Time: 下午 7:48.
 * Explain:配置类
 */
@Configuration
public class ConditionConfig {
    //matches方法返回true的，就运行哪个方法
    @Bean
    @Conditional(WindowsCondition.class)//通过@Condition注解，符合Windows条件则实例化windowsListService
    public ListService windowsListService(){
        return new WindowsListService();
    }

    @Bean
    @Conditional(LinuxCondition.class)//通过@Condition注解,符合Linux条件则实例化linuxListService
    public ListService linuxListService(){
        return new LinuxListService();
    }
}

```

##运行

```
package cn.hncu.p3.p4_conditional;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/12/7.
 * Time: 下午 7:57.
 * Explain:运行类
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConditionConfig.class);
        ListService listService = context.getBean(ListService.class);
        System.out.println(context.getEnvironment().getProperty("os.name")
            +"系统下的列表命令为："
                +listService.showListCmd()
        );
    }
}

```

#运行结果

![](http://img.blog.csdn.net/20161207204718970?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMjY1MjUyMTU=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
