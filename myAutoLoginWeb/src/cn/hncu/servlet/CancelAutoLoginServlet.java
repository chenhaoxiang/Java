package cn.hncu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CancelAutoLoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//取消自动登录，其实就是删除Cookie
		Cookie cookie = new Cookie("autoLogin", "");
		cookie.setPath(request.getContextPath());//这个路径必须和原来的设置为一样，否则没用的
		cookie.setMaxAge(0);//有效期为0即是删除
		response.addCookie(cookie);
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}
}
