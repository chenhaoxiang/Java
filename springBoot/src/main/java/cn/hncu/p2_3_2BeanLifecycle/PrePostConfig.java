package cn.hncu.p2_3_2BeanLifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/14.
 * Time: 上午 11:29.
 * Explain:配置类
 */
@Configuration
@ComponentScan("cn.hncu.p2_3_2BeanLifecycle")
public class PrePostConfig {

    //为BeanWayService准备的配置方法
    //initMethod和destroyMethod指定BeanWayService类的init和destroy方法在构造之后、Bean销毁之前执行
    @Bean(initMethod = "init",destroyMethod = "destroy")
    BeanWayService beanWayService(){
        return new BeanWayService();
    }

    //为JSR250WayService准备的配置方法
    @Bean
    JSR250WayService jsr250WayService(){
        return new JSR250WayService();
    }


}
