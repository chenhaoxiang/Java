package cn.hncu.demo.service;

import cn.hncu.demo.dao.DemoJdbcDao;
import cn.hncu.domain.Dept;

public class DemoServiceImpl {
	private DemoJdbcDao dao = new DemoJdbcDao();
	public Dept queryDeptById(Dept dept) {
		return dao.queryDeptById(dept);
	}
	public void addDept(Dept dept) {
		dao.addDept(dept);
	}
		
}
