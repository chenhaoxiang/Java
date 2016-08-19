package cn.hncu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharsetFilter implements Filter{
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		chain.doFilter(request, response);
	}
	@Override
	public void destroy() {
	}
}
