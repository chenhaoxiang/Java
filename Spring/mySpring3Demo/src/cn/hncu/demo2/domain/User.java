package cn.hncu.demo2.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class User {
	private String name;
	private Integer age;
	private List<Object> pets;
	private Map<String, Object> map;
	private Set<Object> set;
	private Object objs[];
	private Cat cat;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	
	public List<Object> getPets() {
		return pets;
	}

	public void setPets(List<Object> pets) {
		this.pets = pets;
	}

	
	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	
	
	public Set<Object> getSet() {
		return set;
	}

	public void setSet(Set<Object> set) {
		this.set = set;
	}

	
	
	public Object[] getObjs() {
		return objs;
	}

	public void setObjs(Object[] objs) {
		this.objs = objs;
	}

	public Cat getCat() {
		return cat;
	}

	public void setCat(Cat cat) {
		this.cat = cat;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", pets=" + pets
				+ "\r\n map=" + map + "\r\n set=" + set + "\r\n objs="
				+ Arrays.toString(objs) + ", cat=" + cat + "]";
	}

}
