package cn.hncu.p3.p7_fortest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/12/9.
 * Time: 下午 9:27.
 * Explain:测试类---源码在src/test/java下
 */
@RunWith(SpringJUnit4ClassRunner.class)//在SpringJUnit4ClassRunner在Junit环境下提供Spring TextContext Framework的功能
@ContextConfiguration(classes = {TestConfig.class})//@ContextConfiguration用来加载配置ApplicationContext，其中classes属性用来加载配置类
@ActiveProfiles("dev")
public class DemoBeanIntegrationTests {
    @Autowired//可以使用普通的@Autowired注入Bean
    private TestBean testBean;

    @Test//测试代码，通过Junit的Assert来校验结果是否和预期的一样
    public void prodBeanShouldInject(){
        String expected = "production profile";
        String actual = testBean.getContent();
        System.out.println(actual);
        Assert.assertEquals(expected,actual);//用来查看对象中存的值是否是期待的值，与字符串比较中使用的equals()方法类似；
    }
}
