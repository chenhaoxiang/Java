package cn.hncu;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.hncu.beanUtils.MyBeanUtils;
import cn.hncu.beanUtils.MyBeanUtils1;
import cn.hncu.domain.Book;
import cn.hncu.domain.User;

public class TestDemo {
	
	@Test
	@SuppressWarnings("unchecked")
	public void test() {
		Map<String, Object> map = new HashMap();
		map.put("uuid", "001");
		map.put("name", "Jack");
		map.put("age", 20);
		
		Map<String, Object> map2 = new HashMap();
		map2.put("uuid", "001");
		map2.put("name", "红楼梦");
		map2.put("inPrice", 20.5);
		//数据可能不全
		map2.put("num", 123);
		try {
			User user = MyBeanUtils.populate(User.class, map);
			System.out.println(user);
			Book book = MyBeanUtils.populate(Book.class, map2);
			System.out.println(book);
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test1() {
		Map map = new HashMap();
		map.put("uuid", "001");
		map.put("name", "Jack");
		map.put("age", 20);
		
		Map map2 = new HashMap();
		map2.put("uuid", "001");
		map2.put("name", "红楼梦");
		map2.put("inPrice", 20.5);
		//数据可能不全
		map2.put("num", 123);
		try {
			User user =  (User) MyBeanUtils1.populate(User.class, map);
			System.out.println(user);
			Book book =  (Book) MyBeanUtils1.populate(Book.class, map2);
			System.out.println(book);
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
	}
	
	
}
