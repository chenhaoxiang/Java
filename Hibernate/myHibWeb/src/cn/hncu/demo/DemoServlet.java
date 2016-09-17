package cn.hncu.demo;


import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hncu.demo.service.DemoServiceImpl;
import cn.hncu.domain.Student;
import cn.hncu.utils.BaseServlet;

public class DemoServlet extends BaseServlet {
	DemoServiceImpl service = new DemoServiceImpl();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		List<Student> list = service.queryAllStudents();
		req.getSession().setAttribute("list", list);
		
		req.getRequestDispatcher("/jsps/demo.jsp").forward(req, resp);
	}
	
	public void delStudent(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		String studId = req.getParameter("studId");
		Student stud = new Student();
		
		stud.setsId(studId);
		
		service.delStudent(stud);
		
		resp.sendRedirect(getServletContext().getContextPath()+"?time="+(new Date().getTime()));
	}
	
	public void addStudent(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		String studId = req.getParameter("studId");
		String studName = req.getParameter("studName");
		String strAge = req.getParameter("age");
		Integer age = Integer.valueOf(strAge);
		String deptId = req.getParameter("deptId");
		Student stud = new Student();
		stud.setsId(studId);
		
		//System.out.println(studName);//正常汉字
		
		stud.setsName(studName);
		stud.setsAge(age);
		stud.setDeptId(deptId);
		
		service.addStudent(stud);
		
		resp.sendRedirect(getServletContext().getContextPath());
	}
	
	public void queryStudents(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		String studId = req.getParameter("studId");
		String studName = req.getParameter("studName");
		String deptId = req.getParameter("deptId");
		Student stud = new Student();
		stud.setsId(studId);
		stud.setsName(studName);
		stud.setDeptId(deptId);
		
		List<Student> qlist = service.queryStudents(stud);
		req.getSession().setAttribute("qlist", qlist);
		PrintWriter out = resp.getWriter();
		out.print("1"); //坑:不能使用out.println("1")
	}

}
