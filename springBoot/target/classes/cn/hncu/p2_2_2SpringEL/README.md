
#分析
先简单介绍下Spring EL。
Spring EL 也就是Spring表达式语言，支持在xml和注解中使用表达式，类似于JSP的EL表达式语言。

Spring开发中我们可能经常涉及到调用各种资源的情况，包含普通文件、网址、配置文件、系统环境变量等，我们可以使用Spring的表达式语言实现资源的注入。

Spring主要在注解@Value的参数中使用表达式。

本示例演示实现以下几种情况:
1、注入普通的字符串
2、注入操作系统属性
3、注入表达式运算结果
4、注入其他Bean的属性
5、注入文件内容
6、注入网址内容
7、注入属性文件

在本节演示中，我遇到一个问题，已在此博客中解决，如有朋友遇到，请参考本篇博客解决:
<a href="http://blog.csdn.net/qq_26525215/article/details/53155760" target='_blank'>【错误解决】[Maven] cannot be opened because it does not exist错误[文件无法编译到target目录下的解决方法]</a>

进行本示例的演示，需要先配置好Maven和Spring哦、
见:
<a href="http://blog.csdn.net/qq_26525215/article/details/53010442" target='_blank'>【Spring】基于IntelliJ IDEA搭建Maven</a>


#示例

因为需要将file转换成字符串，我们增加commons-io可以简化文件的相关操作、
在pom文件中增加如下代码:
```
<!--简化文件操作-commons-io-->
        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>2.4</version>
        </dependency>
```

然后，在当前类的目录下新建test.txt。内容随意。
我的内容如下:
```
测试文件内容:Spring
```

然后再新建test.properties文件，内容如下，当然，你也可以自己修改:
```
project.name=SpringEL
project.author=chenhaoxiang
```

##写需要被注入的Bean:

```
package cn.hncu.p2_2_2SpringEL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/13.
 * Time: 下午 9:06.
 * Explain:被注入的Bean
 */
@Service
public class DemoService {
    @Value("DemoService类的属性")//注入字符串
    private String another;
    public String getAnother() {
        return another;
    }
    public void setAnother(String another) {
        this.another = another;
    }
}

```

##增加配置类:

```
package cn.hncu.p2_2_2SpringEL;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/13.
 * Time: 下午 9:11.
 * Explain:配置类
 */
@Configuration
@ComponentScan("cn.hncu.p2_2_2SpringEL")
@PropertySource("classpath:cn/hncu/p2_2_2SpringEL/test.properties")
public class ElConfig {

    @Value("I LOVE YOU!")//注入字符串
    private String normal;

    @Value("#{systemProperties['os.name']}")//获取操作系统名
    private String osName;

    @Value("#{ T(java.lang.Math).random() * 100.0 }")//注入表达式结果
    private double randomNumber;

    @Value("#{demoService.another}")//注入其他Bean的属性
    private String fromAnother;

    @Value("${project.name}")//注入配置文件
    private String projectName;

    @Value("classpath:cn/hncu/p2_2_2SpringEL/test.txt")
    private Resource testFile;//注意这个Resource是:org.springframework.core.io.Resource;

    @Autowired //注入配置文件
    private Environment environment;

    @Value("http://www.chaojijuhui.com")//注入网址资源
    private Resource testUrl;

    @Bean //注入配置文件
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    public void outputResource(){
        try {
            System.out.println("normal:"+normal);
            System.out.println("osName:"+osName);
            System.out.println("randomNumber:"+randomNumber);
            System.out.println("fromAnother:"+fromAnother);
            System.out.println("projectName:"+projectName);
            System.out.println("测试文件:"+IOUtils.toString(testFile.getInputStream()));
            System.out.println("配置文件project.author:"+environment.getProperty("project.author"));
            System.out.println("网址资源:"+IOUtils.toString(testUrl.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
```

注入配置配件需要使用@PropertySource指定文件地址，若使用@Value注入，则要配置一个PropertySourcesPlaceholderConfigurer的Bean。
注意，@Value("${project.name}")使用的是"`$`"而不是"#"。
上面的类演示了这2中配置配件的方式！


##运行类:

```
package cn.hncu.p2_2_2SpringEL;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/13.
 * Time: 下午 11:44.
 * Explain:运行类
 */
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ElConfig.class);
        ElConfig resourceService = context.getBean(ElConfig.class);
        resourceService.outputResource();
        context.close();
}

}

```

#运行结果:

![](http://img.blog.csdn.net/20161114103747136)

