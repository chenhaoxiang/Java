
#分析

Spring的依赖注入的最大亮点就是你所有的Bean对Spring容器的存在是没有意识的。

也就是说，你可以把你的容器换成别的容器，如Google Guice，这时Bean之间的耦合度很低。

但是在实际项目中，基本上不可避免的要用到Spring容器本身的功能资源，这时你的Bean必须要意识到Spring容器的存在，才能调用Spring所提供的资源，这就是所谓的Spring Aware。

其实Spring Aware本来就是Spring设计用来框架内部使用的，如果使用了Spring Aware，你的Bean就会和Spring框架耦合。也就不能换容器了。

现在把Spring提供的Aware接口列出来:

|   Spring提供的Aware接口 ||
| ------------- |:-------------:|
| BeanNameAware | 获得容器中Bean的名称 |
| BeanFactoryAware | 获得当前bean factory，这样可以调用容器的服务 |
| ApplicationContextAware* | 当前的application context，这样可以调用容器的服务 |
| MessageSourceAware | 获得message source，这样可以获得文本信息 |
| ApplicationEventPublisherAware | 应用实践发布器，可以发布事件 |
| ResourceLoaderAware | 获得资源加载器，可以获得外部资源文件 |

Spring Aware的目的是为了让Bean获得Spring容器的服务。

因为ApplicationContext接口集成了MessageSource接口、ApplicationEventPublisher接口和ResourceLoader接口，所以Bean继承ApplicationContextAware可以获得Spring容器的所有服务，但是，原则上我们还是用到什么接口了，就实现什么接口。

在这里的示例，简单的演示BeanNameAware接口和ResourceLoaderAware接口。

一样的，进行本示例的演示，需要先配置好Maven和Spring哦、
见:
<a href="http://blog.csdn.net/qq_26525215/article/details/53010442" target='_blank'>【Spring】基于IntelliJ IDEA搭建Maven</a>

#示例

因为要演示外部资源，所以先准备好一个外部文件资源，
我就建在java文件目录下，test.txt文件，内容:
```
测试文件的内容
```

##Spring Aware 演示Bean

```
package cn.hncu.p3.p1_SpringAware;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/16.
 * Time: 下午 6:37.
 * Explain:Spring Aware演示Bean
 */
@Service
public class AwareService implements BeanNameAware,ResourceLoaderAware {
    //实现BeanNameAware,ResourceLoaderAware接口，获得Bean名称和资源加载的服务

    private String beanName;
    private ResourceLoader loader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {//实现ResourceLoaderAware需要重写setResourceLoader方法
        this.loader = resourceLoader;
    }

    @Override
    public void setBeanName(String beanName) {//实现BeanNameAware需要重写setBeanName方法
        this.beanName = beanName;
    }

    public void outputResult(){
        System.out.println("Bean的名称为:"+beanName);
        Resource resource = loader.getResource("cn/hncu/p3/p1_SpringAware/test.txt");

        try {
            System.out.println("ResourceLoader加载的文件内容为: "+ IOUtils.toString(resource.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

```

##配置类

```
package cn.hncu.p3.p1_SpringAware;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/16.
 * Time: 下午 6:48.
 * Explain:配置类
 */
@Configuration
@ComponentScan("cn.hncu.p3.p1_SpringAware")
public class AwareConfig {
}

```

##运行类

```
package cn.hncu.p3.p1_SpringAware;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/16.
 * Time: 下午 6:49.
 * Explain:运行类
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AwareConfig.class);

        AwareService awareService = context.getBean(AwareService.class);
        awareService.outputResult();

        context.close();
    }

}

```

#运行结果

![](http://img.blog.csdn.net/20161116193220921)

