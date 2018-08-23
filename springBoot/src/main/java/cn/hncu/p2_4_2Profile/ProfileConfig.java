package cn.hncu.p2_4_2Profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/14.
 * Time: 下午 8:41.
 * Explain:Profile配置
 */
@Configuration
public class ProfileConfig {

    @Bean
    @Profile("dev")//Profile为dev时实例化devDemoBean
    public DemoBean devDemoBean(){
        return new DemoBean("from development profile");
    }

    @Bean
    @Profile("prod")//Profile为prod时实例化prodDemoBean
    public DemoBean prodDemoBean(){
        return new DemoBean("from production profile");
    }
}
