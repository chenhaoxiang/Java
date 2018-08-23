package cn.hncu.p1_3_3_aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/9.
 * Time: 上午 11:41.
 * Explain:运行类
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
        DemoAnnotationService demoAnnotationService = context.getBean(DemoAnnotationService.class);
        demoAnnotationService.add();

        DemoMethodService demoMethodService = context.getBean(DemoMethodService.class);
        demoMethodService.add();

        context.close();
    }
}
