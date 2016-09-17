package cn.hncu.domain;

import java.util.HashSet;
import java.util.Set;

public class Dept {
	private String dId;
	private String dName;

	//※声明一个专用于存储多方的一个集合及setter-getter
	private Set<Student> students= new HashSet<Student>();
	
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	public String getdId() {
		return dId;
	}
	public void setdId(String dId) {
		this.dId = dId;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
}
