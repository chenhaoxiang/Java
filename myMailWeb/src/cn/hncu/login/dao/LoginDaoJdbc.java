package cn.hncu.login.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.hncu.domain.User;
import cn.hncu.pubs.C3p0Pool;

public class LoginDaoJdbc implements LoginDAO{
    public User login(User u){
    	String sql = "select * from users where name=? and pwd=? and active='1' ";
    	QueryRunner run = new QueryRunner(C3p0Pool.getDataSource());
    	try {
			User user = run.query(sql, new BeanHandler<User>(User.class) , u.getName(),u.getPwd());
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
    }
}
