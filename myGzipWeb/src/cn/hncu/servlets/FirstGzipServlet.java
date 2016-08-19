package cn.hncu.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstGzipServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");

		String str = "网页压缩数据hncuhdncu网页压缩数据hncuh数据hncuhdncu网页压缩数据hncuh数据hncuhdncu网页压缩数据hncuhdncu网页压缩数据hncuhdncu";

		System.out.println("原大小:" + str.getBytes("utf-8").length);

		// 压缩输出--把源数据压缩输出到baout内存流中
		ByteArrayOutputStream baout = new ByteArrayOutputStream();

		GZIPOutputStream gout = new GZIPOutputStream(baout);
		gout.write(str.getBytes("utf-8"));
		gout.close();

		// 从baout内存流中把压缩后的数据取出来，输出到客户端
		byte dest[] = baout.toByteArray();
		System.out.println("压缩后的大小:" + dest.length);
		
		//输出之前告诉客户端:我们的数据是gzip格式，然后输出
		response.setHeader("Content-Encoding", "gzip");
		response.setContentLength(dest.length);
		OutputStream out = response.getOutputStream();
		out.write(dest);
		out.close();
	}

}
