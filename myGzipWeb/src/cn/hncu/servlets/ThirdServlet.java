package cn.hncu.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ThirdServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		String str="网页压缩数据hncuhdncu网页压缩数据hncuh数据hncuhdncu网页压缩数据hncuh数据hncuhdncu网页压缩数据hncuhdncu网页压缩数据hncuhdncu";
		System.out.println("原大小:"+ str.getBytes("utf-8").length);
		
		PrintWriter out = response.getWriter();
		//out.write(str);
		out.println(str);
		out.close();
	}

}
