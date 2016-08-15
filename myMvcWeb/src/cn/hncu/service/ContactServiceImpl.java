package cn.hncu.service;

import java.util.List;

import cn.hncu.dao.ContactDAO;
import cn.hncu.dao.ContactDAOImpl;
import cn.hncu.dao.UserDAO;
import cn.hncu.domain.Contact;

public class ContactServiceImpl implements ContactIService{
    //注入dao
	ContactDAO dao = new ContactDAOImpl();
	
	@Override
	public boolean del(String[] uuids) {
		return dao.del(uuids);
	}


	@Override
	public boolean addContact(String userUuid, Contact contacts) {
		return dao.addContact(userUuid, contacts);
	}


	@Override
	public List<Contact> getQueryContacts(Contact contact,String userUuid) {
		return dao.getQueryContacts(contact,userUuid);
	}

}
