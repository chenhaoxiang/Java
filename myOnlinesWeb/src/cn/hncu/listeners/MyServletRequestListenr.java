package cn.hncu.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyServletRequestListenr implements ServletRequestListener{
	
	//有一个请求就会运行这里
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		ServletContext sct = sre.getServletContext();
		new MyThread(sct).start();
	}
	
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
	}
}

class MyThread extends Thread{
	private ServletContext sct = null;
	private static Object obj = new Object();
	public MyThread(ServletContext sct) {
		this.sct=sct;
	}

	@Override
	public void run() {
		synchronized (obj) {
			sct.setAttribute("count", (Integer) sct.getAttribute("count") + 1);
		}
	}
}