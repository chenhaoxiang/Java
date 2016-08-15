package cn.hncu.dbcp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class DbcpUtil {
	private static DataSource pool;
	private static ThreadLocal<Connection> t = new ThreadLocal<Connection>();
	static{
		
		try {
			//读取配置文件
			Properties p = new Properties();
			p.load(DbcpUtil.class.getResourceAsStream("dbcp.properties"));// 配置文件和当前类的class放在一起
			pool = BasicDataSourceFactory.createDataSource(p);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//返回DataSource--池
	public static DataSource getDataSource(){
		return pool;
	}
	
	public static Connection getConnection() throws SQLException{
		//从本地线程管理对象t中拿
		Connection con = t.get();
		if(con==null){
			con=pool.getConnection();
			//放入t中
			t.set(con);
		}
		return con;
	}
	
}
