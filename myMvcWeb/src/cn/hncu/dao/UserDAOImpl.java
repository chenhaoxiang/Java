package cn.hncu.dao;

import java.util.List;
import java.util.UUID;

import cn.hncu.domain.Contact;
import cn.hncu.domain.User;
import cn.hncu.utils.Dom4jDocument;

public class UserDAOImpl implements UserDAO{
	@Override
	public User login(User user) {
		//按理应该访问数据库的，这里就假定用户名长度大于2且密码长度大于2即登录成功
		if(user.getName()==null||user.getPwd()==null){
			return null;
		}
		user = Dom4jDocument.login(user);
		return user;
	}
	
	@Override
	public List<Contact> getUserContacts(String userUuid) {
		return Dom4jDocument.getUserContacts(userUuid);
	}
	
	@Override
	public boolean addUser(User user) {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		user.setUuid(uuid);
		return Dom4jDocument.addUser(user);
	}
	
}	
