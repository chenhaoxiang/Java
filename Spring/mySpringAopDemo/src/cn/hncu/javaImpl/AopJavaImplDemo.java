package cn.hncu.javaImpl;

import java.lang.reflect.Method;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.AfterAdvice;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;

/**
 * 纯Java的方式实现切面(拦截)技术
 * @author 陈浩翔
 * 2016-9-1
 */
public class AopJavaImplDemo {
	
	//如果你对动态代理有过了解了，对下面的代码会很好理解的
	@Test
	public void demo1(){
		//准备好需要被代理的原型对象
		Person p = new Person();
		ProxyFactory factory = new ProxyFactory();//ProxyFactoryBean的功能比ProxyFactory强
		factory.setTarget(p);//给代理工厂一个原型对象
		
		//构造切面
		//切面=切点 + 通知
		
		//切点
		JdkRegexpMethodPointcut cut = new JdkRegexpMethodPointcut();
		//cut.setPattern("cn.hncu.javaImpl.Person.run");//可以直接给方法全名
		//或者给正则表达式
		cut.setPattern(".*run.*");//.号匹配除"\r\n"之外的任何单个字符。*号代表零次或多次匹配前面的字符或子表达式
		
		//通知
		Advice advice = new MethodInterceptor() {
			//哈哈，看到这个是不是和动态代理中的那个方法很像
			@Override
			public Object invoke(MethodInvocation methodInv) throws Throwable {
				System.out.println("前面拦拦...");
				Object resObj = methodInv.proceed();//放行
				System.out.println("后面拦拦...");
				return resObj;
			}
		};
		//切面 = 切点 + 通知
		Advisor advisor = new DefaultPointcutAdvisor(cut, advice);
		
		factory.addAdvisor(advisor);//给代理工厂一个切面
		Person p2 = (Person) factory.getProxy();//从代理工厂中获取一个代理后的对象
		
		try {
			p2.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
		p2.say();//不会拦
		p2.run(3333);
	}
	
	
	@Test
	public void demo2(){
		ProxyFactoryBean factory = new ProxyFactoryBean();
		factory.setTarget(new Person());//给代理工厂一个原型对象
		
		//切面 = 切点 + 通知
		//切点
		JdkRegexpMethodPointcut cut = new JdkRegexpMethodPointcut();
		cut.setPatterns(new String[]{".*run.*",".*say.*"});//可以配置多个正则表达式
		
		//通知 前切面---不需要放行，原方法也能执行
		Advice before = new MethodBeforeAdvice() {
			@Override
			public void before(Method method, Object[] args, Object obj)
					throws Throwable {
				System.out.println("before...拦截");
			}
		};
		
		Advice after = new AfterReturningAdvice() {
			
			@Override
			public void afterReturning(Object returnValue, Method method,
					Object[] args, Object target) throws Throwable {
				System.out.println("afterReturning...拦截");
			}
		};
		
//		Advice throwsAdvice = new A();
//		Advice afterAdvice = new AfterAdvice() {
//		}; 
		
		
		Advice around = new MethodInterceptor() {
			
			@Override
			public Object invoke(MethodInvocation invocation) throws Throwable {
				System.out.println("MethodInterceptor...前面拦截");
				Object returnValue = invocation.proceed();//放行
				System.out.println("MethodInterceptor...后面拦截");
				return returnValue;
			}
		};
		
		//切面 = 切点 + 通知
		Advisor beforeAdvisor = new DefaultPointcutAdvisor(cut, before);
		Advisor afterAdvisor = new DefaultPointcutAdvisor(cut,after);
		Advisor aroundAdvisor = new DefaultPointcutAdvisor(cut, around);
//		Advisor throwsAdviceAdvisor = new DefaultPointcutAdvisor(cut, throwsAdvice);
		
		// 给代理工厂一个切面 ---注意,添加的顺序的拦截动作执行的顺序是有关系的!!!
		//factory.addAdvisors(beforeAdvisor,afterAdvisor,aroundAdvisor);
		//factory.addAdvisors(beforeAdvisor,aroundAdvisor,afterAdvisor);
		factory.addAdvisors(aroundAdvisor,beforeAdvisor,afterAdvisor);
		
		Person p2 = (Person) factory.getObject();//从代理工厂中获取一个代理后的对象
		
		p2.run(2222);
	}
	
	@Test
	public void demo3(){
		ProxyFactoryBean factory = new ProxyFactoryBean();
		factory.setTarget(new Person());//给代理工厂一个原型对象
		
		//切面 = 切点 + 通知
		//切点
		JdkRegexpMethodPointcut cut = new JdkRegexpMethodPointcut();
		cut.setPatterns(new String[]{".*run.*",".*say.*"});//可以配置多个正则表达式
		
		Advice throwsAdvice = new ThrowException();
		
		//切面 = 切点 + 通知
		Advisor throwsAdviceAdvisor = new DefaultPointcutAdvisor(cut, throwsAdvice);
		
		factory.addAdvisors(throwsAdviceAdvisor);
		
		Person p2 = (Person) factory.getObject();//从代理工厂中获取一个代理后的对象
		
		p2.run();
		p2.run(2222);
	}
	
	
}
