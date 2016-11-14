package cn.hncu.p2_2_2SpringEL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/13.
 * Time: 下午 9:06.
 * Explain:被注入的Bean
 */
@Service
public class DemoService {
    @Value("DemoService类的属性")//注入字符串
    private String another;
    public String getAnother() {
        return another;
    }
    public void setAnother(String another) {
        this.another = another;
    }

}
