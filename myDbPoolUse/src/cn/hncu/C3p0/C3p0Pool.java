package cn.hncu.C3p0;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0Pool {
	//我们的这个包装，只是为了把c3p0池做成让每个线程(客户端)获得的是同一个连接，方便做b/s框架下的事务
	private static DataSource pool;
	private static ThreadLocal<Connection> t = new ThreadLocal<Connection>();
	static{
		pool = new ComboPooledDataSource("demo");
		//这里的参数视你的配置文件而定，也可以不写，用默认的配置
	}
	
	public static DataSource getDataSource(){
		return pool;
	}
	
	public static Connection getConnection() throws SQLException{
		Connection con = t.get();
		if(con==null){
			con=pool.getConnection();
			t.set(con);
		}
		return con;
	}
}
