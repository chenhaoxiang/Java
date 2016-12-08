package cn.hncu.p3.p5_annotation;

import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/12/8.
 * Time: 下午 8:17.
 * Explain:服务Bean
 */
@Service
public class DemoService {
    public void outputResult(){
        System.out.println("从组合注解配置照样获得的bean");
    }
}
