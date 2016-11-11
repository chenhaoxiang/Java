package cn.hncu.p2_1_1Scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2016/11/11.
 * Time: 上午 11:09.
 * Explain:Singleton---默认Spring-Scope
 */
@Service//默认@Scope为Singleton-相当于添加
//@Scope("singleton")
public class DemoSingletonService {
}
