package cn.hncu.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hncu.domain.Contact;
import cn.hncu.service.ContactIService;
import cn.hncu.service.ContactServiceImpl;

public class QueryServlet extends HttpServlet {
	
	//注入
	ContactIService service = new ContactServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//收集数据
		String name = (String) request.getParameter("name");
		String strAge = (String) request.getParameter("age");
		String tel = (String) request.getParameter("tel");
//		System.out.println("####name=="+name);
//		System.out.println("####strAge=="+strAge);
//		System.out.println("####tel=="+tel);
		
		if(name==null||name.trim().length()<1){
			name="";
		}
		
		if(tel==null||name.trim().length()<1){
			tel="";
		}
		
		if(strAge==null||strAge.trim().length()<1){
			strAge="";
		}
		
		Integer age=-1;
		try {
			if(!strAge.equals("")){
				age = Integer.parseInt(strAge);
			}
		} catch (NumberFormatException e) {
			response.getWriter().print("<h1>年龄格式不正确！</h1>");
			response.getWriter().print("<a href='javascript:history:go(-1);'>返回上一页</a>");
			return ;
		}
//		System.out.println("####age=="+age);
		
		String userUuid = (String) request.getSession().getAttribute("userUuid");
		
//		System.out.println("###uuid=="+userUuid);
		
		//组织参数
		Contact contact = new Contact();
		contact.setAge(age);
		contact.setName(name);
		contact.setTel(tel);
		//调用service层
		List<Contact> contacts = service.getQueryContacts(contact, userUuid);
		if(contacts==null){
			response.getWriter().print("<h1>没有查找到联系人！</h1>");
			return ;
		}
		
		for(Contact c: contacts){
			System.out.println(c);
		}
		
		request.setAttribute("contactsQ", contacts);
		
		request.getRequestDispatcher("/jsps/queryShow.jsp").forward(request, response);
	}

}
