package cn.hncu.pubs;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharacterFilter implements Filter{
	private String charset;
	//黑名单
	private Set<String> set = new HashSet<String>();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		charset = filterConfig.getInitParameter("charset");
		
		//到数据库中把黑名单加载进来，这里简单模拟一下
		//set.add("127.0.0.1");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(charset);
		
		//以下演示黑名单过滤技术
		String ip = request.getRemoteAddr();;//获得客户端的IP
		if(set.contains(ip)){
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().println("你已被列入黑名单!");
		}else{//放行
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
	}
   
}
