package cn.hncu.xmlImpl.aspectj;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AspectjXmlDemo {
	@Test
	public void demo1(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("cn/hncu/xmlImpl/aspectj/aspectj.xml");
		Person p = ctx.getBean(Person.class);
		p.run();
		p.run(10);
		p.say();
		p.sayHi("Jack");
		p.say("Tom", 666);
	}
}
