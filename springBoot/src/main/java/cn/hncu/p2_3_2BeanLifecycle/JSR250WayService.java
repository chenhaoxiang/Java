package cn.hncu.p2_3_2BeanLifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/14.
 * Time: 上午 11:23.
 * Explain:使用JSR250形式的Bean
 */
public class JSR250WayService {
    public JSR250WayService() {
        super();
        System.out.println("初始化构造函数-JSR250WayService:"+this.getClass());
    }

    @PostConstruct//@PostConstruct这个注解表明该方法在构造函数执行之后执行
    public void init(){
        System.out.println("jsr250-init方法");
    }

    @PreDestroy//这个注解表明该方法在Bean销毁之前执行
    public void destroy(){
        System.out.println("jsr250-destroy方法");
    }
}
