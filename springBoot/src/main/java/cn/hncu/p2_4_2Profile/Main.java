package cn.hncu.p2_4_2Profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/14.
 * Time: 下午 8:51.
 * Explain:运行类
 */
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("dev");//先将活动的Proofile设置为prod
        context.register(ProfileConfig.class);//后置注册Bean的配置类，不然会报Bean未定义的错误
        context.refresh();//刷新容器

        DemoBean demoBean = context.getBean(DemoBean.class);

        System.out.println(demoBean.getContent());

        context.close();
    }
}
