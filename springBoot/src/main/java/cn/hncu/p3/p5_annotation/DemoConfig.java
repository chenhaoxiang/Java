package cn.hncu.p3.p5_annotation;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/12/8.
 * Time: 下午 8:19.
 * Explain:配置类--组合注解
 */
@WiselyConfiguration("cn.hncu.p3.p5_annotation")
//自定义注解，扫描的所有的bean来源于value值所对应的包路径下
//使用@WiselyConfiguration组合注解替代@Configuration和@ComponentScan
public class DemoConfig {
}
