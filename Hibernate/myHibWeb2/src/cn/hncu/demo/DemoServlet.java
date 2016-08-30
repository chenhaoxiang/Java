package cn.hncu.demo;


import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hncu.demo.service.DemoServiceImpl;
import cn.hncu.domain.Dept;
import cn.hncu.domain.Student;
import cn.hncu.utils.BaseServlet;

public class DemoServlet extends BaseServlet {
	DemoServiceImpl service = new DemoServiceImpl();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		
	}
	
	public void queryDeptById(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		String deptId = req.getParameter("deptId");
		if(deptId==null||deptId.trim().length()==0){
			resp.sendRedirect(getServletContext().getContextPath());
			return;
		}
		Dept dept = new Dept();
		dept.setdId(deptId);
		dept = service.queryDeptById(dept);
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("deptName", dept.getdName());
		map.put("qlist", dept.getStudents());
		
		req.getSession().setAttribute("map", map);
		resp.sendRedirect(getServletContext().getContextPath());
	}
	
	public void addDept(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		String deptId = req.getParameter("deptId");
		String deptName = req.getParameter("deptName");
		if(deptName==null||deptName.trim().equals("")){
			deptName=null;
		}
		String studId = req.getParameter("studId");
		String studName = req.getParameter("studName");
		String studAge = req.getParameter("studAge");
		int age = Integer.parseInt(studAge);
		
		Dept dept = new Dept();
		dept.setdId(deptId);
		dept.setdName(deptName);
		
		Student s1 = new Student();
		s1.setsId(studId);
		s1.setsName(studName);
		s1.setsAge(age);
		s1.setDept(dept);//多方进行设置外键
		
		//把多方添加到一方的集合中
		dept.getStudents().add(s1);
		
		service.addDept(dept);
		
		resp.sendRedirect(getServletContext().getContextPath());
	}
	
}
