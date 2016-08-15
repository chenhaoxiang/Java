package cn.hncu.dao;

import java.util.List;

import cn.hncu.domain.Contact;
import cn.hncu.utils.Dom4jDocument;

public class ContactDAOImpl implements ContactDAO {

	@Override
	public boolean del(String[] uuids) {
		boolean boo=true;
		for(String uuid:uuids){
			boo = Dom4jDocument.del(uuid);
			if(boo==false){
				return boo;
			}
		}
		return boo;
	}

	@Override
	public boolean addContact(String userUuid, Contact contacts) {
		return Dom4jDocument.addContact(userUuid, contacts);
	}

	@Override
	public List<Contact> getQueryContacts(Contact contact,String userUuid) {
		return Dom4jDocument.getQueryContacts(contact, userUuid);
	}


}
