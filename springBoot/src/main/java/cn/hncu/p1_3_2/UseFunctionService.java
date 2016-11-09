package cn.hncu.p1_3_2;

import cn.hncu.p1_3_1.*;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/4.
 * Time: 下午 11:10.
 */
//注意！此处没有使用@Service声明Bean
public class UseFunctionService {
    FunctionService functionService;
    //此处没有使用@Autowired注解注入Bean
    public void setFunctionService(FunctionService functionService){
        this.functionService = functionService;
    }

    public String sayHello(String word){
        return functionService.sayHello(word);
    }
}
