package cn.hncu.p1_3_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/3.
 * Time: 上午 9:04.
 */
@Service
//使用@Service注解声明当前UseFunctionService类是Spring管理的一个Bean
public class UseFunctionService {
    @Autowired
    //使用@Autowired将FunctionService的尸体Bean注入到UseFunctionService中，
    //让UseFunctionService具备FunctionService的功能，此处使用@Inject或者@Resource注解是等效的。
    FunctionService functionService;
    public String sayHello(String word){
        return functionService.sayHello(word);
    }
}
