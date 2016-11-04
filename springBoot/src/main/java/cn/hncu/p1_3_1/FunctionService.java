package cn.hncu.p1_3_1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/3.
 * Time: 上午 9:03.
 */
@Service
//使用@Service注解声明当前FunctionService类是Spring管理的一个Bean。其中，使用@Component,@Service,@Repository和
//@Controller是等效的。
public class FunctionService {
    public String sayHello(String word){
        return "Hello "+word +" !";
    }
}
