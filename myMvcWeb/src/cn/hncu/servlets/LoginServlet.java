package cn.hncu.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hncu.domain.Contact;
import cn.hncu.domain.User;
import cn.hncu.service.UserIService;
import cn.hncu.service.UserServiceImpl;

public class LoginServlet extends HttpServlet {
	
	
	//注入service
	UserIService service = new UserServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//1、收集参数
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String code = request.getParameter("code");
		String realCode="";
		Cookie[] cs = request.getCookies();
		for(Cookie c:cs){
			if(c.getName().equals("realCode")){
				realCode=c.getValue();
				break;
			}
		}
		
		//System.out.println("rc="+realCode);
		//判断验证码
		if(!code.equals(realCode)){
			response.getWriter().print("<h1>验证码错误！</h1>");
			return;
		}
		
		//2组织参数
		User user = new User();
		user.setName(name);
		user.setPwd(pwd);
		
		//3调用service层
		user = service.login(user);
		//System.out.println(user);
		//4根据service层的返回结果，导向不同的结果页面---直接到前台去防护了
		
		if(user==null){
			response.getWriter().print("<h1>用户名或密码错误！</h1>");
			return ;
			
		}
		
		//System.out.println(request.getLocalAddr());
		
		List<Contact> contacts = new ArrayList<Contact>();
		//通过user的uuid拿到所有的联系人
		contacts=service.getUserContacts(user.getUuid());
		
		request.getSession().setAttribute("contacts", contacts);
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("userUuid",user.getUuid());
		
		request.getRequestDispatcher("/jsps/contacts.jsp").forward(request, response);
	}
}
