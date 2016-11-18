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
