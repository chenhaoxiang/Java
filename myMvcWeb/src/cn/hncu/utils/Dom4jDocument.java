package cn.hncu.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import cn.hncu.domain.Contact;
import cn.hncu.domain.User;

public class Dom4jDocument {
	
	private static Document dom=null;
	private static String path;
	//静态块！只会在类加载的时候运行一次
	static{
		
		try {
			SAXReader sax = new SAXReader();
			//因为我们的资源已经从myelipse中发布到tomcat服务器中了，所以跟原来的纯Java项目不一样了。
			//利用当前类找到它的类加载器，然后通过该类加载器再去获得资源路径
			path = Dom4jDocument.class.getClassLoader().getResource("users.xml").getPath();
			 //getClassLoader()返回：加载此对象所表示的类或接口的类加载器
			//public URL getResource(String name)返回：读取资源的 URL 对象；如果找不到该资源，或者调用者没有足够的权限获取该资源，则返回 null。
			//此方法首先搜索资源的父类加载器；如果父类加载器为 null，则搜索的路径就是虚拟机的内置类加载器的路径。
			//public String getPath()获取此 URL 的路径部分。 
			dom = sax.read(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * 保存到服务器-本地
	 * @return 布尔型
	 */
	public static boolean save(){
		
		try {
			XMLWriter w = new XMLWriter(new FileOutputStream(path));
			w.write(dom);
			w.close();
		}catch (UnsupportedEncodingException e) {
			return false;
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * @param uuid - 通过联系人的uuid-删除联系人
	 * @return
	 */
	public static boolean del(String uuid){
		Node node = dom.selectSingleNode("//contacts[@uuid=\""+uuid+"\"]");
		if(node==null){
			return false;
		}
		node.getParent().remove(node);
		save();
		return true;
	}
	
	
	/**
	 * 添加联系人
	 * @param uuid--通过user的uuid
	 * @return
	 */
	public static boolean addContact(String userUuid,Contact contacts){
		Element userNode = (Element)dom.selectSingleNode("//user[@uuid=\""+userUuid+"\"]");
		if(userNode==null){
			return false;
		}
		Element contactE =  userNode.addElement("contacts");
		contactE.addAttribute("uuid", contacts.getUuid());
		contactE.addAttribute("name", contacts.getName());
		contactE.addAttribute("age", ""+contacts.getAge());
		contactE.addAttribute("tel", contacts.getTel());
		
		save();
		return true;
	}
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public static boolean addUser(User user){
		//先防范重复name的
		if(dom.selectSingleNode("//user[@name=\""+user.getName()+"\"]")!=null){
			return false;
		}
		
		try {
			Element root = dom.getRootElement();
			Element u = root.addElement("user");
			u.addAttribute("uuid", user.getUuid());
			u.addAttribute("name", user.getName());
			u.addAttribute("pwd", user.getPwd());
			save();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * 用户登录时，返回封装的user或false
	 * @param user
	 * @return
	 */
	public static User login(User user){
		Element userE = (Element)dom.selectSingleNode("//user[@name=\""+user.getName()+"\"]");
		if(userE==null){
			return null;
		}
		user.setUuid(userE.attributeValue("uuid"));
		return user;
	}
	
	public static List<Contact> getUserContacts(String userUuid){
		List<Contact> contacts = new ArrayList<Contact>();
		
		Element user = (Element) dom.selectSingleNode("//user[@uuid=\""+userUuid+"\"]");
		
		List<Element> lists =  user.elements();
		for(Element list:lists){
			Contact c = new Contact();
			c.setName(list.attributeValue("name"));
			c.setAge(Integer.parseInt(list.attributeValue("age")));
			c.setTel(list.attributeValue("tel"));
			c.setUuid(list.attributeValue("uuid"));
			contacts.add(c);
		}
		return contacts;
	}
	
	
	public static List<Contact> getQueryContacts(Contact contact,String userUuid){
		List<Contact> contacts = new ArrayList<Contact>();
		
		Element user = (Element) dom.selectSingleNode("//user[@uuid=\""+userUuid+"\"]");
		if(user==null){
			return null;
		}
		
		String name = contact.getName().trim();
		Integer age = contact.getAge();
		String tel = contact.getTel().trim();
		
//		System.out.println("name="+name);
//		System.out.println("age="+age);
//		System.out.println("tel="+tel);
		
		List<Element> lists =  user.elements();
		for(Element c : lists){
			//System.out.println("c=="+c);
			if(name.equals("")||c.attributeValue("name").indexOf(name)!=-1){
				if(age==-1||c.attributeValue("age").indexOf(age+"")!=-1){
					if(tel.equals("")||c.attributeValue("tel").indexOf(tel)!=-1){
						Contact cc = new Contact();
						cc.setName(c.attributeValue("name"));
						cc.setAge(Integer.parseInt(c.attributeValue("age")));
						cc.setTel(c.attributeValue("tel"));
						cc.setUuid(c.attributeValue("uuid"));
						contacts.add(cc);
					}
				}
			}
		}
		return contacts;
	}
	
}
