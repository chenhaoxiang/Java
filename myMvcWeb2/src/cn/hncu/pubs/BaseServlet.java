package cn.hncu.pubs;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 陈浩翔
 *
 * 2016-8-15
 */
public abstract class BaseServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String cmd = req.getParameter("cmd");
		if(cmd==null){
			cmd="execute";
		}
		
		try {
			System.out.println("this:"+this);
			//下面这句不能访问私有方法
			//Method m = this.getClass().getMethod(cmd, HttpServletRequest.class,HttpServletResponse.class );
			
			//如果要用类反射实现访问类的私有方法，则需要这2步,这样子类的方法就不用设置成public了。
			//1获得私有方法  
			Method m = this.getClass().getDeclaredMethod(cmd, HttpServletRequest.class,HttpServletResponse.class );
			//2设置私有方法可以被访问 
			m.setAccessible(true);
			m.invoke(this, req,resp);//这里的this是子类对象，谁调用这里的，这个this就是谁	
			
			//下面这几句是给大家启发Struts框架的思想
//			String str =(String) m.invoke(this, request,response);
//			String resPage = "";//从配置struts.xml文件中读取str所对应的结果页面的地址
//			request.getRequestDispatcher(resPage).forward(request,response);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	//如果没有参数cmd为null，转向的界面，由子类自己实现
	public abstract void execute(HttpServletRequest request, HttpServletResponse response);
}
