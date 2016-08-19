package cn.hncu.servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecondServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		String str = "网页压缩数据hncuhdncu网页压缩数据hncuh数据hncuhdncu网页压缩数据hncuh数据hncuhdncu网页压缩数据hncuhdncu网页压缩数据hncuhdncu";
		System.out.println("原大小:" + str.getBytes("utf-8").length);
		
		OutputStream out = response.getOutputStream();
		out.write(str.getBytes("utf-8"));
		//注意，虽然MyEclipse环境设置的是utf-8编码，但本句“str.getBytes()”却是以gbk方式编码---应该是Tomcat中的JVM采用的方式
	}
}
