package cn.hncu.p2_1_1Scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/11.
 * Time: 上午 11:23.
 * Explain:编写Prototype的Bean
 */
@Service
@Scope("prototype")
public class DemoPrototypeService {
}
