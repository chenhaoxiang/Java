package cn.hncu.demo.service;

import java.util.List;

import cn.hncu.demo.dao.DemoJdbcDao;
import cn.hncu.domain.Student;

public class DemoServiceImpl {
	private DemoJdbcDao dao = new DemoJdbcDao();

	public List<Student> queryAllStudents() {
		return dao.queryAllStudents();
	}

	public void delStudent(Student stud) {
		dao.delStudent(stud);
	}
	
	public void addStudent(Student stud) {
		dao.addStudent(stud);
	}

	public List<Student> queryStudents(Student stud) {
		return dao.queryStudents(stud);
	}
	
}
