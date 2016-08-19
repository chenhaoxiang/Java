package cn.hncu.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		List<HttpSession> guests = (List<HttpSession>) getServletContext().getAttribute("guests");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(guests!=null){
			out.print("所有访问者:<br/>");
			for (HttpSession s :guests) {
				Date d = new Date(s.getCreationTime());
				out.println("ID:"+s.getId()+"," + sdf.format(d)+"<br/>");
			}
		}
		
		
		out.println("<hr/>");
		out.println("<h3>以下是已登录用户信息</h3>");
		
		List<HttpSession> logins = (List<HttpSession>) getServletContext().getAttribute("logins");
		
		if(logins!=null){
			for(HttpSession s :logins){
				out.println("ID:" + s.getId() + ", Name:"+s.getAttribute("name") + ",IP:"+s.getAttribute("ip") + "<br/>");
			}
		}

	}

}
