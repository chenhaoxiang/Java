package cn.hncu.demo2;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.hncu.demo2.domain.User;

public class Demo2 {
    
	//演示spring中<bean>属性注入的一些细节
	@Test
	public void demo(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext2.xml");
		User user = ctx.getBean("user",User.class);
		System.out.println(user);
	}
	
	
}
