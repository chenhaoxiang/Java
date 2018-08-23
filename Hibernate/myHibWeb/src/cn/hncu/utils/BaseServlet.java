package cn.hncu.utils;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String cmd = req.getParameter("cmd");
		if (null == cmd || cmd.trim().equals("")) {
			cmd = "execute";
		}
		try {
			Method method = this.getClass().getMethod(cmd,
					HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, req, resp);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("没有此方法：" + e.getMessage(), e);
		}catch(InvocationTargetException e){
			throw new RuntimeException("目标方法执行失败：" + e.getMessage(), e);
		}catch(IllegalAccessException e){
			throw new RuntimeException("你可能访问了一个私有的方法：" + e.getMessage(), e);
		}catch(Exception e){
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	public abstract void execute(HttpServletRequest req,
			HttpServletResponse resp) throws Exception;
}
