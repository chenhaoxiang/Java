package cn.hncu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.hncu.beanUtils.MyBeanUtils;
import cn.hncu.beanUtils.MyBeanUtils1;
import cn.hncu.domain.Address;
import cn.hncu.domain.Book;
import cn.hncu.domain.Person;
import cn.hncu.domain.User;

public class TestDemo {
	
	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void test2() {
		Map<String, Object> p = new HashMap();
		p.put("name", "Jack");
		p.put("age", 100);
		p.put("address", new Address("湖南", "长沙"));
		
		List lists = new ArrayList();
		lists.add(new Book("B001", "红楼梦", 25.00, 53.23, 500));
		lists.add(new User("U001", "李四", 25));
		lists.add("嵌套使用");
		p.put("lists", lists);
		
		Map map = new HashMap();
		map.put("user", new User("MU002", "MapUser", 30));
		map.put("string", "map中的字符串");
		p.put("map", map);
		
		try {
			Person person = MyBeanUtils.populate(Person.class, p);
			
			System.out.println(person);
			
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	@SuppressWarnings("unchecked")
	public void test() {
		Map<String, Object> map = new HashMap();
		map.put("uuid", "001");
		map.put("name", "Jack");
		map.put("age", 20);
		
		Map<String, Object> map2 = new HashMap();
		map2.put("uuid", "001");
		map2.put("name", "张三");
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
		map2.put("name", "张三");
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
