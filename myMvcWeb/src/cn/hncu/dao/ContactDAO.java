package cn.hncu.dao;

import java.util.List;

import cn.hncu.domain.Contact;

public interface ContactDAO {
	public boolean addContact(String userUuid,Contact contacts);
	public boolean del(String[] uuids);
	public List<Contact> getQueryContacts(Contact contact,String Useruuid);
}
