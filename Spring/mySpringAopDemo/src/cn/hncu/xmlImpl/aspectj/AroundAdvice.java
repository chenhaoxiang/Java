package cn.hncu.xmlImpl.aspectj;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AroundAdvice implements MethodInterceptor{
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("前面拦截....");
		Object resObj = invocation.proceed();//放行
		System.out.println("后面拦截.....");
		return resObj;
	}

}
