package cn.hncu.demo1;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import cn.hncu.demo1.domain.Person;
import cn.hncu.demo1.login.LoginAction;

public class TestDemo {
	
	@Test
	public void demo1(){
		//此方法已在3.0版本中过时，不建议使用
		//BeanFactory factory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		
		//Spring3.0建议采用下面这种方式使用容器
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		Person p = (Person) ctx.getBean("p");//通过配置的id获得，需要强转
		System.out.println(p);
		
		Person p2 = ctx.getBean(Person.class);//通过Person的Class对象去获得，不需要强转
		System.out.println(p2);
	}
	
	@Test//演示依赖(XML)注入
	public void demo2(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		LoginAction action = ctx.getBean(LoginAction.class);
		//LoginAction action = ctx.getBean("login",LoginAction.class);
		action.execute();
	}
	
	
}
