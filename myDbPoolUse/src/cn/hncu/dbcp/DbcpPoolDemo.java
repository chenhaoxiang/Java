package cn.hncu.dbcp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.junit.Test;

public class DbcpPoolDemo {

	// 纯Java方式设置参数，使用dbcp池
	@Test
	public void testDbcp() {
		BasicDataSource pool = new BasicDataSource();// 连接池
		pool.setUsername("root");
		pool.setPassword("1234");
		pool.setDriverClassName("com.mysql.jdbc.Driver");
		pool.setUrl("jdbc:mysql://127.0.0.1:3306/hncu?useUnicode=true&characterEncoding=utf-8");

		System.out.println(pool.getMaxIdle());// 最大空闲时间。如果一个用户获取一个连接，不用，多长时间会被强行收回
		System.out.println(pool.getMaxWaitMillis());// 在抛出异常之前,池等待连接被回收的最长时间（当没有可用连接时）。设置为-1表示无限等待。
		System.out.println(pool.getInitialSize());// 初始化时有几个Connection
		System.out.println(pool.getMaxTotal());// 最多能有多少个Connection
		
		System.out.println("----------------");
		// pool.setMaxTotal(20);//可以我们自己设置池的相关参数，如最大连接数

		// 从它的池中获取连接
		for (int i = 0; i < 20; i++) {
			Connection con = null;
			try {
				con = pool.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(i + ":" + con.hashCode());
		}
	}

	// 通过配置文件方式设置参数，使用dbcp池
	@Test
	public void testPropertyFile() throws Exception {
		Properties p = new Properties();
		p.load(DbcpPoolDemo.class.getResourceAsStream("dbcp.properties"));// 配置文件和当前类的class放在一起
		// p.load(DbcpPoolDemo.class.getClassLoader().getResourceAsStream("dbcp.properties"));//配置文件要放在src(bin)的根目录---classpath的根
		DataSource pool = BasicDataSourceFactory.createDataSource(p);
		// 从它的池中获取连接
		for (int i = 0; i < 20; i++) {
			Connection con = pool.getConnection();
			System.out.println(con.hashCode());
			if (i % 2 == 0) {
				con.close();
			}
		}
	}

}
