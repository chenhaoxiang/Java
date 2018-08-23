package cn.hncu.p2_3_2BeanLifecycle;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/14.
 * Time: 上午 11:19.
 * Explain:使用@Bean形式的Bean
 */
public class BeanWayService {
    public BeanWayService() {
        super();
        System.out.println("初始化构造函数-BeanWayService:"+this.getClass());
    }

    public void init(){
        System.out.println("BeanWayService-init方法");
    }

    public void destroy(){
        System.out.println("BeanWayService-destroy方法");
    }
}
