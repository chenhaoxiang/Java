package cn.hncu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hncu.domain.User;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//从页面接收登录信息
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String time = request.getParameter("time");
		
		//System.out.println(name+","+pwd+","+time);
		
		User user = new User();
		user.setName(name);
		user.setPwd(pwd);
		
		if(name!=null && name.trim().length()!=0 && pwd!=null){
			if(name.equals(pwd)){//按理应该到后台去验证登录是否成功，这里直接以用户名和密码相同为登录成功
				request.getSession().setAttribute("user", user);
				//登录成功，就往客户端写一个cookie,将用户名和密码存到cookie中-应该进行加密!
				//为了能够兼容中文，要进行编码
				name = URLEncoder.encode(name, "utf-8");
				pwd = URLEncoder.encode(pwd, "utf-8");
				
				Cookie cookie = new Cookie("autoLogin", name+"#!#"+pwd);
				cookie.setPath(request.getContextPath());//权限：本项目中的类都可以访问该cookie
				//有效期
				cookie.setMaxAge( 60 * Integer.parseInt(time) );
				response.addCookie(cookie);//存储到客户端
			}else{
				request.getSession().setAttribute("error", "密码错误!");
			}
		}else{
			request.getSession().setAttribute("error", "请输入用户名!");
		}
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}

}
