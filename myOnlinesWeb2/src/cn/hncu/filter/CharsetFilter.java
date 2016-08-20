package cn.hncu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CharsetFilter implements Filter{
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		//获取该用户的ip,存储到它的session对象中
		HttpServletRequest req = (HttpServletRequest) request;
		if(req.getSession().getAttribute("ip")==null){
			req.getSession().setAttribute("ip", req.getRemoteAddr());
			//这里的req和request是同一个对象！！！
			//所以传过去的参数还可以是request
		}
		
		//new一个线程统计访问量
		new MyThread(request.getServletContext()).start();
		
		//放行
		chain.doFilter(request, response);
	}
	@Override
	public void destroy() {
	}
}

class MyThread extends Thread{
	private ServletContext servletContext =null;
	private static Object boj = new Object();
	
	public MyThread(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public void run() {
		synchronized (boj) {
			servletContext.setAttribute("count",(Integer)servletContext.getAttribute("count")+1);
		}
	}
	
}