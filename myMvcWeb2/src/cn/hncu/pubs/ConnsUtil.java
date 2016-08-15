package cn.hncu.pubs;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnsUtil {
	
	private static List<Connection> pool = new ArrayList<Connection>();
	private static final int NUM = 3;
	
	private static ThreadLocal<Connection> t = new ThreadLocal<Connection>();
	
	static{
		
		try {
			//读取配置文件
			Properties p = new Properties();
			p.load(ConnsUtil.class.getClassLoader().getResourceAsStream("jdbc.properties"));
			String driver = p.getProperty("driver");

			String url = p.getProperty("url");
			String user = p.getProperty("username");
			String password = p.getProperty("password");
			
			Class.forName(driver);
			
			for(int i=0;i<NUM;i++){
				final Connection conn = DriverManager.getConnection(url, user, password);
				//System.out.println(conn.getClass().getClassLoader());//AppClassLoader
				
				//使用动态代理,代理conn对象，实现对close方法的拦截
				Object obj = Proxy.newProxyInstance(ConnsUtil.class.getClassLoader(),
							new Class[]{Connection.class},
							new InvocationHandler() {
														
							@Override
							public Object invoke(Object proxy, Method method, Object[] args)
							throws Throwable {
								//拦截close方法
								if(method.getName().equalsIgnoreCase("close") &&(args==null||args.length==0) ){
									pool.add((Connection)proxy);
									//proxy为代理后的对象
									t.set(null);//把存储在本地线程管理类中的当前线程对应的对象设为空
									return null;
								}
								//conn为被代理的对象
								return method.invoke(conn, args);
							}
						});
				pool.add( (Connection)obj);//把代理后的conn对象即obj加入池中
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized Connection getConnection() throws InterruptedException{
		//从本地线程管理对象t中拿
		Connection con = t.get();
		if(con==null){
			if(pool.size()<=0){
				Thread.sleep(50);
				return getConnection();
			}
			con=pool.remove(0);
			//放入到t中
			t.set(con);
		}
		return con;
	}
}
