package cn.hncu.demo1.domain;

public class Person {
	private String name;
	private int age;

	public Person() {
		System.out.println("执行Person的构造方法....");
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

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
}
