package cn.hncu.p2_5_2ApplicationEvent;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/15.
 * Time: 下午 8:50.
 * Explain:事件监听器
 */
@Component
public class DemoListener implements ApplicationListener<DemoEvent> {//实现ApplicationListener接口，并指定监听的事件类型
    @Override
    public void onApplicationEvent(DemoEvent demoEvent) {//使用onApplicationEvent方法对消息进行接受处理
        String msg = demoEvent.getMsg();
        System.out.println(this.getClass()+"监听到了bean-demoPublisher发布的消息:"+msg);
    }
}
