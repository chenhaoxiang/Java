package cn.hncu.p1_3_3_aop;

import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/9.
 * Time: 上午 11:16.
 * Explain:使用注解的被拦截类
 */
@Service
public class DemoAnnotationService {
    @Action(name = "@Action---DemoAnnotationService.add操作")
    public void add(){
        System.out.println("DemoAnnotationService.add...");
    }
}
