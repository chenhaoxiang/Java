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
