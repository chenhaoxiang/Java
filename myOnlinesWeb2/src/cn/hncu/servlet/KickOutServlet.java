package cn.hncu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class KickOutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		Map<String, HttpSession> onLines = (Map<String, HttpSession>) getServletContext().getAttribute("onLines");
		
		if(onLines.containsKey(id)){
			HttpSession session = onLines.get(id);//得到该session
			session.invalidate();//移除该session
			out.println("该用户已被踢出!");
		}else{
			out.println("该用户已经不存在!");
	    }
	}
}
