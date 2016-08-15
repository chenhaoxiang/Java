package cn.hncu.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hncu.domain.User;
import cn.hncu.service.UserIService;
import cn.hncu.service.UserServiceImpl;

public class AddUserServlet extends HttpServlet {
	
	//注入
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
		
		//收集参数
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
		
		//判断验证码
		if(!code.equals(realCode)){
			response.getWriter().print("<h1>验证码错误！</h1>");
			return;
		}
		
		//防范空参
		if(name==null || name.trim().equals("")||pwd==null || pwd.trim().equals("")){
			response.getWriter().print("<h1>用户名或密码为空，请重新输入！</h1>");
			response.getWriter().print("<a href='"+request.getContextPath()+"/index.jsp'>返回登录页</a>");
			return; 
		}
		
		//封装参数
		User user = new User(name, pwd);
		
		//访问service
		boolean boo = service.addUser(user);
		
		if(boo){
			response.getWriter().print("<h1>恭喜，注册成功!</h1>");
			response.getWriter().print("<a href='"+request.getContextPath()+"/index.jsp'>返回登录页</a>");
		}else{
			response.getWriter().print("<h1>用户名已存在，请重新注册！</h1>");
			response.getWriter().print("<a href='"+request.getContextPath()+"/index.jsp'>返回登录页</a>");
		}
	}
}
