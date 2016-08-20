package cn.hncu.listeners;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		Map<String, HttpSession> onLines = (Map<String, HttpSession>) se.getSession().getServletContext().getAttribute("onLines");
		if(onLines==null){//第一个来访问的
			onLines = Collections.synchronizedMap(new HashMap<String, HttpSession>() );//使用同步技术的Map
			se.getSession().getServletContext().setAttribute("onLines", onLines);
		}
		onLines.put(se.getSession().getId(), se.getSession());
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		Map<String, HttpSession> onLines = (Map<String, HttpSession>) se.getSession().getServletContext().getAttribute("onLines");
		//这里也要防范好！多线程，多个管理同时踢一个人的时候，如果不防范就会出问题
		if(onLines.containsKey( se.getSession().getId() )){
			onLines.remove( se.getSession().getId() );
		}
		
	}
	
	
}
