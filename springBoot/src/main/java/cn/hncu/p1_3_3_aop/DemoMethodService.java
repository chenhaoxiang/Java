package cn.hncu.p1_3_3_aop;

import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/9.
 * Time: 上午 11:23.
 * Explain:使用方法规则的被拦截类
 */
@Service
public class DemoMethodService {
    @Action(name="@Action---DemoMethodService.add操作")
    public void add(){
        System.out.println("DemoMethodService.add...");
    }
}
