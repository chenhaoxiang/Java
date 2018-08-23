package cn.hncu.reg.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hncu.domain.User;
import cn.hncu.reg.sendMail.MySendMailThread;
import cn.hncu.reg.service.IRegService;
import cn.hncu.reg.service.RegServiceImpl;

public class RegServlet extends HttpServlet {
	//依赖注入
	private IRegService service = new RegServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.println("请去注册页面注册！.");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String path = request.getContextPath()+"/index.jsp";
		
		//收集参数 
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		
		//防范一下(这里可用ajax在前台也防范一下)
		if(name==null||name.trim().length()<=0){
			out.println("用户名不能为空，请重新输入!");
			out.println("<a href='"+path+"'>返回登录页</a><br/>");
			return;
		}
		if(pwd==null||pwd.trim().length()<=0){
			out.println("密码不能为空，请重新输入!");
			out.println("<a href='"+path+"'>返回登录页</a><br/>");
			return;
		}
		if(email==null||email.trim().length()<=0){
			out.println("邮箱不能为空，请重新输入!");
			out.println("<a href='"+path+"'>返回登录页</a><br/>");
			return;
		}
		if(email.indexOf("@")==-1){
			out.println("邮箱格式不对，请重新输入!");
			out.println("<a href='"+path+"'>返回登录页</a><br/>");
			return;
		}
		
		//组织JavaBean
		User user = new User();
		user.setName(name);
		user.setPwd(pwd);
		user.setEmail(email);
		//调用service
		user = service.reg(user);
		
		if(user!=null){
			//发送激活邮件
			//这里有一个小知识点，必须新开一个线程来发邮件，不能把发邮件的动作写在这里
			//如果写在这里，用户的前台显示会等待过长时间，不好！
			new MySendMailThread(user).start();
			out.println("您已经注册成功，请去邮箱激活账号后再进行登录，如果没有收到邮件，请稍等!<br/>");
			out.println("<a href='"+path+"'>返回登录页</a><br/>");
			
		}else{
			out.println("很抱歉，服务器繁忙，注册失败，需要重新注册！");
		}
		out.close();
	}
}

