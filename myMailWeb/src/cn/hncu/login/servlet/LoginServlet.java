package cn.hncu.login.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hncu.domain.User;
import cn.hncu.login.service.ILoginService;
import cn.hncu.login.service.LoginServiceImpl;

public class LoginServlet extends HttpServlet {
	//注入
	private ILoginService service = new LoginServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String path = request.getContextPath()+"/index.jsp";
		
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		User user = new User();
		user.setName(name);
		user.setPwd(pwd);

		// 防范一下(这里可用ajax在前台也防范一下)
		if (name == null || name.trim().length() <= 0) {
			out.println("用户名不能为空，请重新输入!");
			out.println("<a href='"+path+"'>返回登录页</a><br/>");
			return;
		}
		if (pwd == null || pwd.trim().length() <= 0) {
			out.println("密码不能为空，请重新输入!");
			out.println("<a href='"+path+"'>返回登录页</a><br/>");
			return;
		}
		user = service.login(user);
		if (user == null) {
			request.getSession().setAttribute("error", "1");
		} else {
			request.getSession().setAttribute("user", user);
		}
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

}
