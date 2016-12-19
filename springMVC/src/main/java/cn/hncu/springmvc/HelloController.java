package cn.hncu.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/12/19.
 * Time: 下午 3:55.
 * Explain:简单控制器
 */
@Controller //利用@Controller注解声明是一个控制器
public class HelloController {
    @RequestMapping("/index")//利用@RequestMapping配置URL和方法之间的映射
    public String hello(){
        return "index";//通过上面ViewResolver的Bean配置，返回值为index，说明我们的页面放置的路径是：/WEB-INF/classes/views/index.jsp
    }
}
