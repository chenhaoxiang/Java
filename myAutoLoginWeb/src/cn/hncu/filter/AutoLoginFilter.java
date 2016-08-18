package cn.hncu.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import cn.hncu.domain.User;

public class AutoLoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if(req.getSession().getAttribute("user")==null){//还没有登录，帮你自动登录
			Cookie cs[] = req.getCookies();
			if(cs!=null){
				for(Cookie c :cs){//找"autoLogin"这个cookie
					if(c.getName().equals("autoLogin")){
						String str = c.getValue();
						String vals[] = str.split("#!#");
						String name = URLDecoder.decode(vals[0], "utf-8");
						String pwd = URLDecoder.decode(vals[1], "utf-8");
						
						//...
						//这里应该再去后台数据库验证是否登录能够成功,这里我就直接以两者相等来判断
						if(name.equals(pwd)){//如果成功则返回一个user对象
							User user = new User();
							user.setName(name);
							user.setPwd(pwd);
							req.getSession().setAttribute("user", user);
							break;
						}
					}
				}
			}
			
		}
		chain.doFilter(req, response);//放行
	}
	@Override
	public void destroy() {
	}
}
