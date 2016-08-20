package cn.hncu.listeners;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//从服务器硬盘把之前存储的点击量数据读取出来
		ServletContext sct = sce.getServletContext();
		String path = sct.getRealPath("/count.txt");
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = br.readLine();
			Integer count = Integer.valueOf(line);
			sct.setAttribute("count", count);
		} catch (Exception e) {
			//如果出异常了，我们认为文件还不存在，服务器第一次启动
			e.printStackTrace();
			sct.setAttribute("count",new Integer(0));
		}
		
	}
	
	//关闭服务器时
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		//把当前servletContext容器中存储的点击量数据 永久化到  服务器硬盘
		ServletContext sct = sce.getServletContext();
		String path = sct.getRealPath("/count.txt");
		
		try {
			PrintWriter pw = new PrintWriter(path);
			pw.write(""+sct.getAttribute("count"));
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	
	}

}
