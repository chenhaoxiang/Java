#分析

在Spring中，通过任务执行器，也就是TaskExecutor来实现多线程和并发编程。

使用ThreadPoolTaskExecutor可实现一个基于线程池的TaskExecutor。
而实际开发中任务一般是非阻碍的，也就是非异步的，所以我们要在配置类中通过@EnableAsync开启对异步任务的支持，并通过在实际执行的Bean的方法中使用@Async注解来声明其是一个异步任务。


进行本示例的演示，需要先配置好Maven和Spring哦、
见:
<a href="http://blog.csdn.net/qq_26525215/article/details/53010442" target='_blank'>【Spring】基于IntelliJ IDEA搭建Maven</a>

#示例

##配置类

首先看一下配置类。
现在全部使用Java配置哦，不用xml了。

```
package cn.hncu.p3.p2_TaskExecutor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/18.
 * Time: 上午 9:35.
 * Explain:配置类
 */
@Configuration
@ComponentScan("cn.hncu.p3.p2_TaskExecutor")
@EnableAsync //利用@EnableAsync注解开启异步任务支持
public class TaskExecutorConfig implements AsyncConfigurer{
    //配置类实现AsyncConfigurer接口并重写getAsyncExcutor方法，并返回一个ThreadPoolTaskExevutor
    //这样我们就获得了一个基于线程池的TaskExecutor
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);//线程池维护线程的最少数量
        taskExecutor.setMaxPoolSize(10);//线程池维护线程的最大数量
        taskExecutor.setQueueCapacity(25);//线程池所使用的缓冲队列
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}

```


##任务执行类

也就是实际运行的，需要异步执行的类

```
package cn.hncu.p3.p2_TaskExecutor;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/18.
 * Time: 上午 10:57.
 * Explain:任务执行类
 */
@Service
public class AsyncTaskService {

    @Async
    //通过@Async注解表明该方法是个异步方法，如果注解在类级别，则表明该类所有的方法都是异步方法。
    // 而这里的方法自动被注入使用ThreadPoolTaskExecutor作为TaskExecutor
    public void executeAsyncTask(Integer i){
        System.out.println("executeAsyncTask:"+i);
    }

    @Async
    public void executeAsyncTaskPlus(Integer i){
        System.out.println("executeAsyncTaskPlus:"+i);
    }

}

```

##运行类

为了测试而写的运行调用方法的类

```
package cn.hncu.p3.p2_TaskExecutor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/18.
 * Time: 上午 11:04.
 * Explain:运行类
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskExecutorConfig.class);

        AsyncTaskService asyncTaskService = context.getBean(AsyncTaskService.class);
        for(int i=0;i<10;i++){
            asyncTaskService.executeAsyncTaskPlus(i);
            asyncTaskService.executeAsyncTask(i+1);
        }
        context.close();

    }

}

```

#运行结果
![](http://img.blog.csdn.net/20161118113251428)

运行结果长了一点，所以只传了这么一点。
但是已经能够看出了，假如是原来那样的，是异步执行，那么肯定偶数行的输出比前一个奇数行的输出是大1的。

结果不是那样，它们不是异步进行的，在这里由一个主线程(main线程)。
for循环里面，每运行一行调用方法的，就会开一个线程。
也就是说，你每次的运行结果可能会不一样！
所以，如果你的运行结果和我的不一样，不要慌哦。