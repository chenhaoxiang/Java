package cn.hncu.p3.p5_annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/12/8.
 * Time: 下午 4:00.
 * Explain:组合注解
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//表明这个注解应该被 javadoc工具记录. 默认情况下,javadoc是不包括注解的. 但如果声明注解时指定了 @Documented,
// 则它会被 javadoc 之类的工具处理, 所以注解类型信息也会被包括在生成的文档中.
@Configuration//组合@Configuration元注解  bean注解
@ComponentScan//组合@ComponentScan元注解  自动扫描对应value（package路径）值下面的所有bean
public @interface WiselyConfiguration {
    String[] value() default {};//覆盖value参数
    //就是覆盖@ComponentScan注解中的value参数----必须要写，否则组合注解中放入value值时会报错
}
