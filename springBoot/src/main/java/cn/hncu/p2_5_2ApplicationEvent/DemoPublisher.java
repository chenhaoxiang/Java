package cn.hncu.p2_5_2ApplicationEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/15.
 * Time: 下午 10:39.
 * Explain:事件发布类
 */
@Component
public class DemoPublisher {
    @Autowired
    ApplicationContext applicationContext;//注入ApplicationContext用来发布事件

    public void publish(String msg){
        DemoEvent demoEvent = new DemoEvent(this,msg);
        applicationContext.publishEvent(demoEvent);//在这里的时候，会去运行DemoListener中的onApplicationEvent方法
        System.out.println("消息:"+demoEvent.getMsg());
        //使用ApplicationContext的publishEvent方法来发布
    }

}
