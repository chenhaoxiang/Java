package cn.hncu.p1_3_3_aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/9.
 * Time: 上午 11:42.
 * Explain:配置类
 */
@Configuration
@ComponentScan("cn.hncu.p1_3_3_aop")
@EnableAspectJAutoProxy //使用@EnableAspectJAutoProxy注解开启Spring对AspectJ代理的支持
public class AopConfig {
}
