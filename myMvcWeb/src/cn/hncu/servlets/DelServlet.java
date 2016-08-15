package cn.hncu.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hncu.service.ContactIService;
import cn.hncu.service.ContactServiceImpl;

public class DelServlet extends HttpServlet {
	
	//注入
	ContactIService contact = new ContactServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String str = request.getParameter("ids");
		String ids[] = str.split(",");
		
		boolean boo = contact.del(ids);
		//调用后台service,service再调用dao进行实际数据的删除
		
		//System.out.println(boo);
		
		request.setAttribute("succ", boo);
		
		//转向结果页面
		request.getRequestDispatcher("/jsps/delback.jsp").forward(request, response);
	}
}
