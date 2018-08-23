package cn.hncu.p3.p3_taskscheduler;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/22.
 * Time: 下午 10:32.
 * Explain:配置类
 */

@Configuration
@ComponentScan("cn.hncu.p3.p3_taskscheduler")
@EnableScheduling //通过@EnableScheduling注解开启对计划任务的支持
public class TaskScheduleConfig {
}
