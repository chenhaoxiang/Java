package cn.hncu.pubs.tx;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

import cn.hncu.pubs.ConnsUtil;

public class TxProxy implements InvocationHandler{
	private Object srcObj;
	
	public TxProxy(Object srcObj) {
		this.srcObj = srcObj;
	}
	
	/* 版本1
	public static Object getProxy(Object srcObj){
		Object proxiedObj = Proxy.newProxyInstance(TxProxy.class.getClassLoader(),
							srcObj.getClass().getInterfaces(),
							new TxProxy(srcObj));
		return proxiedObj;
	}
	*/
	/* 版本2
	public static<T> T getProxy(Object srcObj, Class<T> cls){
		Object proxiedObj = Proxy.newProxyInstance(TxProxy.class.getClassLoader(),
							srcObj.getClass().getInterfaces(),
							new TxProxy(srcObj));
		return (T)proxiedObj;
	}
	*/
	/* 版本3
	public static<T> T getProxy(Class<T> cls){
		Object srcObj = null;
		try {
			srcObj = cls.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		Object proxiedObj = Proxy.newProxyInstance(TxProxy.class.getClassLoader(),
							srcObj.getClass().getInterfaces(),
							new TxProxy(srcObj));
		return (T)proxiedObj;
	}
	*/
	//版本4
	public static<T> T getProxy(T srcObj){
		Object proxiedObj = Proxy.newProxyInstance(TxProxy.class.getClassLoader(),
							srcObj.getClass().getInterfaces(),
							new TxProxy(srcObj));
		return (T)proxiedObj;
	}
	
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		//判断method方法是不是添加了注解
		if(method.isAnnotationPresent(Transaction.class)){
			Connection con=null;
			Object returnValue =null;
			
			try {
				con = ConnsUtil.getConnection();
				con.setAutoCommit(false);
				System.out.println("在动态代理中开启一个事务....");
				returnValue = method.invoke(srcObj, args);
				con.commit();
				System.out.println("在动态代理中提交一个事务....");
			} catch (Exception e) {
				con.rollback();
				System.out.println("在动态代理中回滚一个事务....");
			}finally{
				if(con!=null){
					try {
						con.setAutoCommit(true);
						con.close();
					} catch (Exception e) {
						throw new RuntimeException("数据库连接关闭失败", e);
					}
				}
			}
			return returnValue;
		}else{//没有添加注解,直接放行
			return method.invoke(srcObj, args);
		}
	}
}
