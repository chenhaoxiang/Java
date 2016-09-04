package cn.hncu.utils;

import java.lang.reflect.Method;
import java.sql.Connection;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodProxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class CloseAdvice implements MethodInterceptor{
	private ThreadLocal<Object> t = new ThreadLocal<Object>();

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		Object obj = t.get();
		if(obj==null){
			final Object con = invocation.proceed();//返回原型对象Connection
			//通过cglib工具进行动态代理
			Callback callback = new net.sf.cglib.proxy.MethodInterceptor() {
				@Override
				public Object intercept(Object proxiedObj, Method method,
						Object[] args, MethodProxy proxy) throws Throwable {
					if(method.getName().equals("close")){
						return null;
					}
					//con为原型Connection对象
					return method.invoke(con, args);
				}
			};
			
			//obj为cglib工具代理后的Connection对象
			obj=Enhancer.create(Connection.class, callback);
			t.set(obj);
		}
		return obj;
	}

	
	
	
	
}
