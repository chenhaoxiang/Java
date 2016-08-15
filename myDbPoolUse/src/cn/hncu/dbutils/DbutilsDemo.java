package cn.hncu.dbutils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ext.ExtQueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.junit.Test;

import cn.hncu.C3p0.C3p0Pool;

public class DbutilsDemo {
	@Test//原来不使用dbUtils工具的数据库查询代码实现
	public void jdbcQuery() throws SQLException{
		List<Person> persons = new ArrayList<Person>();
		Connection con = C3p0Pool.getConnection();
		String sql = "select * from person";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()){
			Person p = new Person();
			p.setId(rs.getString("id"));
			p.setName(rs.getString("name"));
			//p.setAddress(rs.getString("address"));
			p.setAge(Integer.parseInt(rs.getString("age")));
			persons.add(p);
		}
		for(Person p:persons){
			System.out.println(p);
		}
	}
	
	@Test
	public void dbUtilsQuery() throws SQLException{
		QueryRunner run = new QueryRunner(C3p0Pool.getDataSource());
		String sql = "select * from person";
		List<Person> persons = run.query(sql,new BeanListHandler<Person>(Person.class));
		for(Person p:persons){
			System.out.println(p);
		}
	}
	
	@Test
	public void dbUtilsQuery2() throws SQLException{
		QueryRunner run = new QueryRunner(C3p0Pool.getDataSource());
		String sql = "select * from person ";
		List<Map<String, Object>> persons = run.query(sql,new MapListHandler());
		for(Map<String, Object> p:persons){
			System.out.println(p);
		}
	}
	
	//DbUtils工具的使用演示: 增删改--用update()
	//查--用query()方法
	
	//增
	@Test
	public void save() throws SQLException{
		QueryRunner run = new QueryRunner(C3p0Pool.getDataSource());
		//statement方式
		//run.update("insert into person(id,name,address,age) values('P007','Rose','湖南长沙',22) ");
		//prepareStatement方式
		run.update("insert into person(id,name,address,age) values(?,?,?,?)", "P008","Marry","中国西安",34);
	}
	
	@Test
	public void saveTx() throws SQLException{
		QueryRunner run = new QueryRunner(C3p0Pool.getDataSource());
		Connection con = C3p0Pool.getConnection();
		try {
			con.setAutoCommit(false);
			run.update(con, "insert into person(id,name,address,age) values(?,?,?,?)","P011","Tom","浙江杭州",24);
			run.update(con, "insert into person(id,name,address,age) values(?,?,?,?)","P012","Gimo","江苏苏州",45);
			con.commit();
		} catch (Exception e) {
			if(con!=null){
				con.rollback();
				System.out.println("事务回滚了...");
			}
		}finally{
			if(con!=null){
				con.setAutoCommit(true);
				con.close();
			}
		}
	}
	
	
	@Test
	public void query2() throws SQLException{
		QueryRunner run = new QueryRunner(C3p0Pool.getDataSource());
		//封装成BeanList: 如果值对象中的属性名和表中的字段名不一致，那么该属性的值返回的是null
		//解决方法是采用别名，或者修改set**/get**名
		List<Person> persons = run.query("select id,name,address addr,age from person ",new BeanListHandler<Person>(Person.class) );//用属性名 当 字段别名
		for(Person p:persons){
			System.out.println(p);
		}
	}
	
	@Test
	public void query3() throws SQLException{
		QueryRunner run = new QueryRunner(C3p0Pool.getDataSource());
		String sql = "select * from person";
		List<Map<String, Object>> persons = run.query(sql, new MapListHandler());
		for(Map<String, Object> p:persons){
			System.out.println(p);
		}
	}
	
	@Test
	public void query4() throws SQLException{
		QueryRunner run = new QueryRunner(C3p0Pool.getDataSource());
		String sql = "select id,name,address,age from person where name like ? and age > ?";
		List<Person> persons = run.query(sql, new BeanListHandler<Person>(Person.class),"%o%",34);
		for(Person p:persons){
			System.out.println(p);
		}
	}
	
	
	@Test
	public void batch() throws SQLException{
		QueryRunner run = new QueryRunner(C3p0Pool.getDataSource());
		for(int i=1;i<=100;i++){
			String sql = "insert into person values(?,?,?,?)";
			String str = "000"+i;
			str=str.substring(str.length()-3, str.length());
			
			String id1="A"+str;
			String id2="B"+str;
			String params[][]={{id1,"Alice"+i,"中国",i+""},{id2,"Bob"+i,"湖南",i+""}};
			run.batch(sql, params);
		}
	}
	
	
	@Test 
	public void query5(){
		ExtQueryRunner run = new ExtQueryRunner(C3p0Pool.getDataSource());
		List<Person> persons = run.query(Person.class);//不用sql语句，，直接查询Bean-List
		for(Person p:persons){
			System.out.println(p);
		}
	}
	
	@Test
	public void save3(){
		ExtQueryRunner run = new ExtQueryRunner(C3p0Pool.getDataSource());
		Person p = new Person();
		p.setId("A000");
		p.setName("梨子");
		p.setAddress("中国");
		p.setAge(20);
		run.save(p);//不用sql语句，直接存对象
		System.out.println(p);
	}
	
	
	
}
