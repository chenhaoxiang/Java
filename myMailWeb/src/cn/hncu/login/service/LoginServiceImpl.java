package cn.hncu.login.service;

import cn.hncu.domain.User;
import cn.hncu.login.dao.LoginDAO;
import cn.hncu.login.dao.LoginDaoJdbc;

public class LoginServiceImpl implements ILoginService {
	// 注入
	private LoginDAO dao = new LoginDaoJdbc();

	public User login(User u) {
		return dao.login(u);
	}
}
