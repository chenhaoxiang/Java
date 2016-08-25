package cn.hncu.domain;

import java.util.List;
import java.util.Map;

/**
 * @author 陈浩翔
 *
 * 2016-8-25
 */
public class Person {
	private String name;
	private int age;
	private Address address;
	private List lists;
	private Map map;
	public Person() {
		super();
	}
	public Person(String name, int age, Address address, List lists, Map map) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
		this.lists = lists;
		this.map = map;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List getLists() {
		return lists;
	}
	public void setLists(List lists) {
		this.lists = lists;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", address=" + address
				+ ", lists=" + lists + ", map=" + map + "]";
	}
	
}
