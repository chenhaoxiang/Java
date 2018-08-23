package cn.hncu.p1_3_1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/3.
 * Time: 上午 9:09.
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DiConfig.class);
        //使用AnnotationConfigApplicationContext作为Spring容器，接受输入一个配置类作为参数
        UseFunctionService useFunctionService = context.getBean(UseFunctionService.class);
        //获得声明配置的UseFunctionService的Bean
        System.out.println(useFunctionService.sayHello("张三"));
        context.close();
}
}
