package cn.hncu.p2_1_1Scope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/11.
 * Time: 上午 11:43.
 * Explain:运行类
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScopeConfig.class);

        DemoPrototypeService p1 = context.getBean(DemoPrototypeService.class);
        DemoPrototypeService p2 = context.getBean(DemoPrototypeService.class);

        DemoSingletonService s1 = context.getBean(DemoSingletonService.class);
        DemoSingletonService s2 = context.getBean(DemoSingletonService.class);

        System.out.println("p1.equals(p2):"+p1.equals(p2));//false
        System.out.println("p1==p2:"+(p1==p2));//false

        System.out.println("s1.equals(s2):"+s1.equals(s2));//true
        System.out.println("s1==s2:"+(s1==s2));//true
    }
}
