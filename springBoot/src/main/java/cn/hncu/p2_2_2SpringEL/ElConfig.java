package cn.hncu.p2_2_2SpringEL;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/13.
 * Time: 下午 9:11.
 * Explain:配置类
 */
@Configuration
@ComponentScan("cn.hncu.p2_2_2SpringEL")
@PropertySource("classpath:cn/hncu/p2_2_2SpringEL/test.properties")
public class ElConfig {

    @Value("I LOVE YOU!")//注入字符串
    private String normal;

    @Value("#{systemProperties['os.name']}")//获取操作系统名
    private String osName;

    @Value("#{ T(java.lang.Math).random() * 100.0 }")//注入表达式结果
    private double randomNumber;

    @Value("#{demoService.another}")//注入其他Bean的属性
    private String fromAnother;

    @Value("${project.name}")//注入配置文件
    private String projectName;

    @Value("classpath:cn/hncu/p2_2_2SpringEL/test.txt")
    private Resource testFile;//注意这个Resource是:org.springframework.core.io.Resource;

    @Autowired //注入配置文件
    private Environment environment;

    @Value("http://www.chaojijuhui.com")//注入网址资源
    private Resource testUrl;

    @Bean //注入配置文件
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    public void outputResource(){
        try {
            System.out.println("normal:"+normal);
            System.out.println("osName:"+osName);
            System.out.println("randomNumber:"+randomNumber);
            System.out.println("fromAnother:"+fromAnother);
            System.out.println("projectName:"+projectName);
            System.out.println("测试文件:"+IOUtils.toString(testFile.getInputStream()));
            System.out.println("配置文件project.author:"+environment.getProperty("project.author"));
            System.out.println("网址资源:"+IOUtils.toString(testUrl.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
