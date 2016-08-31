package cn.hncu.demo1.login.service;

import cn.hncu.demo1.domain.Person;
import cn.hncu.demo1.login.dao.LoginDAO;


public class LoginServiceImpl implements ILoginService{
	private LoginDAO dao = null;
	//如果要注入，需要注入的成员变量，必须写好set-get方法！
	
	public LoginDAO getDao() {
		return dao;
	}
	public void setDao(LoginDAO dao) {
		this.dao = dao;
	}

	@Override
	public void login(Person p) {
		dao.login(p);
	}

}
