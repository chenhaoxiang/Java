package cn.hncu.p1_3_1;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/3.
 * Time: 上午 9:08.
 */
@Configuration
//@Configuration声明当前类是配置类。
@ComponentScan("cn.hncu.p1_3_1")
//使用@ComponentScan，自动扫描包名下所有使用@Service，@Component，@Repository和@Controller的类，并注册为Bean
public class DiConfig {
}
