package cn.hncu.pubs;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;


public class JspCacheFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse) response;
		//设置缓存时间
		Date date = new Date();
		long time = date.getTime()+1000*60*60*24*5;//缓存5天
		resp.setHeader("expires", ""+time);
		resp.setHeader("pragma", ""+time);
		resp.setHeader("cache-control", ""+time);
		//设置3个，以兼容不同的浏览器
		
		//用修改过的resp往后台传
		chain.doFilter(request, resp);
	}

	@Override
	public void destroy() {
	}
	
}
