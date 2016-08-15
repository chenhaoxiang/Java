package cn.hncu.service;

import java.util.List;

import cn.hncu.dao.UserDAO;
import cn.hncu.dao.UserDAOImpl;
import cn.hncu.domain.Contact;
import cn.hncu.domain.User;

public class UserServiceImpl implements UserIService {
	
	//注入DAO
	UserDAO dao = new UserDAOImpl();
	
	@Override
	public User login(User user) {
		return dao.login(user);
	}

	@Override
	public boolean addUser(User user) {
		return dao.addUser(user);
	}
	
	@Override
	public List<Contact> getUserContacts(String userUuid) {
		return dao.getUserContacts(userUuid);
	}
	
}
