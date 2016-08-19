package cn.hncu.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("不支持GET方式提交注册！");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		
		//在这里，我们假定输入用户名即登录成功
		if(name!=null && name.trim().length()>0 ){
			request.getSession().setAttribute("name", name);
			request.getSession().setAttribute("ip", request.getRemoteAddr());
			out.print("登录成功了。");
		}
		out.close();
	}

}
