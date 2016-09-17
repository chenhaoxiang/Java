package cn.hncu.pubs;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

//我们的这个包装，只是为了把c3p0池做成让每个线程(客户端)获得的是同一个连接，方便做b/s框架下的事务
public class C3p0Pool {

	private static DataSource pool;
	private static ThreadLocal<Connection> t = new ThreadLocal<Connection>();
	static {
		pool = new ComboPooledDataSource();
	}

	public static DataSource getDataSource() {
		return pool;
	}

	public static Connection getConnection() throws SQLException {
		Connection con  = t.get();
		if(con==null){
			con = pool.getConnection();
			t.set(con);
		}
		return con;
	}

}
