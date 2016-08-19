package cn.hncu.listeners;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyHttpSessionListener implements HttpSessionListener {

	//有 游客/用户 来访问了
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		List<HttpSession> guests = (List<HttpSession>) se.getSession().getServletContext().getAttribute("guests");//在线人的集合
		if(guests==null){//第一个访问网站的人--沙发
			guests = new ArrayList<HttpSession>();
			se.getSession().getServletContext().setAttribute("guests", guests);//设置guests属性
		}
		guests.add(se.getSession());//将第一个用户的session添加到在线人集合
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		List<HttpSession> guests = (List<HttpSession>) se.getSession().getServletContext().getAttribute("fuses");
		if(guests.contains(se.getSession())){
			guests.remove(se.getSession());
		}
	}
}
