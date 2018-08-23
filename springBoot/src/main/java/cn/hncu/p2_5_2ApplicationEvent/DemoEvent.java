package cn.hncu.p2_5_2ApplicationEvent;

import org.springframework.context.ApplicationEvent;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/15.
 * Time: 下午 8:39.
 * Explain:自定义事件
 */
public class DemoEvent extends ApplicationEvent{
    private static final long serialVersionUID = 1L;
    private String msg;

    public DemoEvent(Object source,String msg) {
        super(source);
        this.msg=msg;
        System.out.println(this.getClass()+",构造方法");
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
