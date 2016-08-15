package cn.hncu.dbutils;

import org.apache.commons.dbutils.ext.Column;
import org.apache.commons.dbutils.ext.Table;


/**
 * @author 陈浩翔
 *
 * 2016-8-15
 */
@Table(value = "person")
public class Person {
	@Column
	private String id;
	@Column
	private String name;
	
	@Column(value="address")
	public String addr;
	//这里的名字如果和数据库的字段名不同。会出现读取值为null的情况
	//如果要解决，把get和set函数的set/get***写成和数据的字段名一样就可以了。
	//或者在查询的时候取别名如：select id,name,address as addr ,age  from person
	//但是最好还是和数据库的字段名一样比较好
	@Column
	private Integer age;
	public Person() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return addr;
	}
	public void setAddress(String addr) {
		this.addr = addr;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + ", addr=" + addr
				+ ", age=" + age + "]";
	}
	
}
