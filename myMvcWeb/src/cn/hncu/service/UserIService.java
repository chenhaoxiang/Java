package cn.hncu.service;

import java.util.List;

import cn.hncu.domain.Contact;
import cn.hncu.domain.User;

public interface UserIService {
	public User login(User user);
	public boolean addUser(User user);
	public List<Contact> getUserContacts(String userUuid);
}
