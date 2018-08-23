package cn.hncu.xmlImpl;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 采用配置文件的方式使用切面拦截
 * @author 陈浩翔
 * 2016-9-2
 */
public class AopXmlDemo {
	
	@Test//把切点和通知配置成 切面的外部bean
	public void demo1(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("cn/hncu/xmlImpl/1.xml");
		Person p =ctx.getBean("personProxied",Person.class);
		p.run();
		p.say();
	}
	
	@Test//把切点和通知配置成 切面的内部bean
	public void demo2(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("cn/hncu/xmlImpl/2.xml");
		Person p =ctx.getBean("personProxied",Person.class);
		p.run();
		p.say();
	}
	
	@Test//直接在切面bean中配置“切点的正则表达式”，省去“切点bean”的配置
	public void demo3(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("cn/hncu/xmlImpl/3.xml");
		Person p =ctx.getBean("personProxied",Person.class);
		p.run();
		p.say();
	}
	
	@Test//自动代理
	public void demo4(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("cn/hncu/xmlImpl/4.xml");
		Person p =ctx.getBean(Person.class);
		p.run();
		p.say();
	}
	
	@Test//自己实现的自动代理
	public void demo5(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("cn/hncu/xmlImpl/5.xml");
		Person p =ctx.getBean(Person.class);
		p.run();
		p.say();
	}
	
	@Test//自动代理
	public void demo6(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("cn/hncu/xmlImpl/6.xml");
		Person p =ctx.getBean(Person.class);
		p.run();
		p.say();
	}
	
}
