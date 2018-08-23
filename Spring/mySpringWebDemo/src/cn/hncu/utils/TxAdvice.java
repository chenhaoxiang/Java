package cn.hncu.utils;

import java.sql.Connection;

import javax.sql.DataSource;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


//另外一种方法获取Web中的spring容器--实现ApplicationContextAware接口
public class TxAdvice implements MethodInterceptor,ApplicationContextAware{
	private ApplicationContext ctx =null;
	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		this.ctx=ctx;
	}
	
	//通知---这个里面需要拿到dataSource，所以需要先拿到Spring的容器
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		DataSource dataSource = ctx.getBean("dataSource",DataSource.class);
		Connection con = dataSource.getConnection();
		Object res =null;
		try {
			con.setAutoCommit(false);
			//开启一个事务
			res = invocation.proceed();//放行
			con.commit();//提交
			System.out.println("提交一个事务...");
		} catch (Exception e) {
			con.rollback();
			System.out.println("事务回滚...");
		}finally{
			con.setAutoCommit(true);//关闭事务
			con.close();
		}
		return res;
	}
	
}
