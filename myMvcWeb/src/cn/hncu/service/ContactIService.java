package cn.hncu.service;

import java.util.List;

import cn.hncu.domain.Contact;

public interface ContactIService {
	public boolean addContact(String userUuid,Contact contacts);
    public boolean del(String[] uuids);
    public List<Contact> getQueryContacts(Contact contact,String userUuid);
}
