进行本示例的演示，需要先配置好Maven和Spring哦、
见:
<a href="http://blog.csdn.net/qq_26525215/article/details/53010442" target='_blank'>【Spring】基于IntelliJ IDEA搭建Maven</a>

#分析

要实现计划任务，首先通过在配置类注解@EnableScheduling来开启对计划任务的支持，然后在要执行计划任务的方法上注解@Scheduled，声明这是一个计划任务。

Spring通过@Scheduled支持多种类型的计划任务，包含cron、fixDelay、fixRate等。

在本示例中：
使用cron属性可按照指定时间执行，本例写的是每天20点07分执行；

#示例

##计划任务执行类

在这个类中的方法上需要@Scheduled注解配合@EnableScheduling使用。

```
package cn.hncu.p3.p3_taskscheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/22.
 * Time: 下午 10:25.
 * Explain:计划任务执行类
 */
@Service
public class ScheduledTaskService {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000) //通过@Scheduled声明该方法是计划任务，使用fixedRate属性每隔固定时间执行
    public void reportCurrentTime(){
        System.out.println("每隔5秒执行一次 "+dateFormat.format(new Date()));
    }

    @Scheduled(cron = "0 07 20 ? * *" ) //使用cron属性可按照指定时间执行，本例指的是每天20点07分执行；
    //cron是UNIX和类UNIX(Linux)系统下的定时任务
    public void fixTimeExecution(){
        System.out.println("在指定时间 "+dateFormat.format(new Date())+" 执行");
    }
}

```

##配置类

通过@EnableScheduling注解开启对计划任务的支持

```
package cn.hncu.p3.p3_taskscheduler;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/22.
 * Time: 下午 10:32.
 * Explain:配置类
 */

@Configuration
@ComponentScan("cn.hncu.p3.p3_taskscheduler")
@EnableScheduling //通过@EnableScheduling注解开启对计划任务的支持
public class TaskScheduleConfig {
}

```

##运行类

```
package cn.hncu.p3.p3_taskscheduler;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/22.
 * Time: 下午 10:34.
 * Explain:运行类
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskScheduleConfig.class);
    }
}

```

#运行结果

![](http://img.blog.csdn.net/20161209201152733)

