package cn.hncu.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import cn.hncu.pubs.WordsUtil;

public class WordFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		
		//在过滤器中用装饰模式把 原装request的功能增强了
		//---拦截后台调用的getParamter()方法
		MyRequest req = new MyRequest((HttpServletRequest)request);
		
		chain.doFilter(req, response);//放行
	}

	@Override
	public void destroy() {
	}
}

class MyRequest extends HttpServletRequestWrapper{
	public MyRequest(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		String str = super.getParameter(name);
		List<String> list = WordsUtil.getWords();
		for(String word : list){
			str = str.replaceAll(word, "*");
		}
		return str;
	}
	
	
	
}
