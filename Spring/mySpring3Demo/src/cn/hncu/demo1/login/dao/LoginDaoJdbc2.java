package cn.hncu.demo1.login.dao;

import cn.hncu.demo1.domain.Person;

public class LoginDaoJdbc2 implements LoginDAO{

	@Override
	public void login(Person p) {
		System.out.println("dao2,到数据库中读取用户信息以进行登录....");		
		System.out.println("dao2中获取的用户输入信息:"+p);		
	}

}
