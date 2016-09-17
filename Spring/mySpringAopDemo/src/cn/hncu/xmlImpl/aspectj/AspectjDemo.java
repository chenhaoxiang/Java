package cn.hncu.xmlImpl.aspectj;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class AspectjDemo {
	
	@Test
	public void demo(){
		
		ProxyFactoryBean factory = new ProxyFactoryBean();
		factory.setTarget(new Person());
		
		//声明一个aspectj切点
		AspectJExpressionPointcut cut = new AspectJExpressionPointcut();
		
		//设置需要拦截的方法-用切点语言来写
		cut.setExpression("execution( int cn.hncu.xmlImpl.aspectj.Person.run() )");//拦截:空参返回值为int的run方法
		//cut.setExpression("execution( void cn.hncu.xmlImpl.aspectj.Person.*() )");//拦截:空参空返回值的任意方法
		//cut.setExpression("execution (void cn.hncu.xmlImpl.aspectj.Person.*(String))");//拦截:只有1个String类型参数，空返回值的任意方法
		//cut.setExpression("execution( void cn.hncu.xmlImpl.aspectj.Person.*(*) )");//拦截:有1个参数(类型不限)，空返回值的任意方法
		//cut.setExpression("execution( void cn.hncu.xmlImpl.aspectj.Person.*(*,*) )");//拦截:有2个参数(类型不限)，空返回值的任意方法
		//cut.setExpression("execution( void cn.hncu.xmlImpl.aspectj.Person.*(..) )");//拦截:任意(个数和类型)参数，空返回值的任意方法
		//cut.setExpression("execution( int cn.hncu.xmlImpl.aspectj.Person.*(*,..) )");//拦截:至少有1个参数(类型不限)，返回值类型是int的任意方法
		//cut.setExpression("execution( * cn.hncu.xmlImpl.aspectj.Person.*(*,..) )");//拦截:至少有1个参数(类型不限)，返回值类型任意的方法
		//cut.setExpression("execution( * cn.hncu..*son.*(*,..) )");//拦截:cn.hncu包下，类名以"son"结束,至少有1个参数(类型不限)，返回值类型任意的方法
		
		Advice advice = new MethodInterceptor() {
			@Override
			public Object invoke(MethodInvocation invocation) throws Throwable {
				System.out.println("放行前拦截...");
				Object obj = invocation.proceed();//放行
				System.out.println("放行后拦截...");
				return obj;
			}
		};
		
		//切面=切点+通知
		Advisor advisor = new DefaultPointcutAdvisor(cut,advice);
		factory.addAdvisor(advisor);
		Person p = (Person) factory.getObject();
		
		p.run();
		p.run(10);
		p.say();
		p.sayHi("Jack");
		p.say("Tom", 666);
	}
	
	
}
