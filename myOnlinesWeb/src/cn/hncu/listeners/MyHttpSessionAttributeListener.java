package cn.hncu.listeners;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener{
	
	//session添加属性了，就会调用下面的attributeAdded方法
	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		if(se.getName().equals("name")){//代表添加了name属性
			System.out.println("用户"+se.getValue()+"登录了");
			List<HttpSession> logins = (List<HttpSession>) se.getSession().getServletContext().getAttribute("logins");
			
			if(logins==null){
				logins = new ArrayList<HttpSession>();
				se.getSession().getServletContext().setAttribute("logins", logins);
			}
			logins.add(se.getSession());
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
	}
	
}
