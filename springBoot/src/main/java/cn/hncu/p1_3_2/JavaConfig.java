package cn.hncu.p1_3_2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/4.
 * Time: 下午 11:17.
 */
@Configuration
//使用@Configuration注解表明当前类是一个配置类，这意味着这个类里可能有0个或者多个@Bean注解、
//此处没有使用包扫描，是因为所有的Bean都在此类中定义了
public class JavaConfig {
    @Bean
    //使用@Bean注解声明当前方法FunctionService的返回值是一个Bean，Bean的名称是方法名
    public FunctionService functionService(){
        return new FunctionService();
    }

    /*
    @Bean
    public UseFunctionService useFunctionService(){
        UseFunctionService useFunctionService = new UseFunctionService();
        useFunctionService.setFunctionService(functionService());
        //注入FunctionService的Bean时候直接调用functionService()
        return useFunctionService;
    }
    */

    @Bean
    public  UseFunctionService useFunctionService(FunctionService functionService){
        //另外一种注入的方式，直接将FunctionService作为作为参数给useFunctionService()，这也是Spring容器提供的极好的功能。
        //在Spring容器中，只要容器中存在某个Bean，就可以在另外一个Bean的声明方法的参数中直接写入
        UseFunctionService useFunctionService = new UseFunctionService();
        useFunctionService.setFunctionService(functionService);
        return useFunctionService;
    }
}
