package cn.hncu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
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
		String name = request.getParameter("name");
		if(name!=null && name.trim().length()>0){
			User user = new User();
			user.setName(name);
			//用user作为参数到后台登录，这里我们简化了，直接假设登录成功
			if(true){
				Random r = new Random();
				int a = r.nextInt(2);
				if(a%2==0){
					user.setAdmin(true);
				}else{
					user.setAdmin(false);
				}
			}
			request.getSession().setAttribute("user", user);
		}
		
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}

}
