package cn.hncu.C3p0;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0Demo {

	@Test
	// 纯Java方法使用c3p0
	public void C3p0Demo() throws PropertyVetoException, SQLException {
		ComboPooledDataSource pool = new ComboPooledDataSource();
		pool.setUser("root");// 用户姓名
		pool.setPassword("1234");// 用户密码
		pool.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/hncu?useUnicode=true&characterEncoding=utf-8");// MySQL数据库连接url
		pool.setDriverClass("com.mysql.jdbc.Driver");

		// pool可以调用set方法进行Connection池的配置

		// 连接关闭之后，内存会被释放，下次取时会重新开(内存地址不共用)
		for (int i = 0; i < 20; i++) {
			Connection con = pool.getConnection();
			System.out.println(i + ":" + con);
			if (i % 2 == 0) {
				con.close();
			}
		}
	}

	@Test
	// 演示采用配置文件的方式使用c3p0
	public void C3p0PropertyDemo() throws SQLException {
		ComboPooledDataSource pool = new ComboPooledDataSource();//空参，自动到classpath目录下面加载“c3p0-config.xml”配置文件---配置文件的存储位置和名称必须是这样，且使用“默认配置”
		//ComboPooledDataSource pool = new ComboPooledDataSource("demo");//加载“c3p0-config.xml”文件中定义的“demo”这个配置元素
		 for(int i=0;i<25;i++){
			   Connection con = pool.getConnection();
			   System.out.println(i+":"+ con.hashCode());
		   }
	}

}
