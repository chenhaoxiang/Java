package cn.hncu.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hncu.domain.Contact;
import cn.hncu.service.ContactIService;
import cn.hncu.service.ContactServiceImpl;

public class SaveServlet extends HttpServlet {
	
	//注入
	ContactIService service = new ContactServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
    

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("uts-8");
		response.setContentType("text/html;charset=utf-8");
		//1、收集参数
		String name = request.getParameter("name");
		String strAge = request.getParameter("age");
		Integer age;
		try {
			age = Integer.parseInt(strAge);
		} catch (NumberFormatException e) {
			response.getWriter().print("<h1>年龄必须为数字！</h1>");
			return ;
		}
		String tel = request.getParameter("tel");
		
		if(name==null || name.trim().equals("")){
			response.getWriter().print("<h1>姓名不能为空!</h1>");
			return ;
		}
		
		//2、组织参数
		Contact contact = new Contact();
		
		//在这里为contacts补：uuid
		String uuid = UUID.randomUUID().toString().replace("-", "");
		
		contact.setUuid(uuid);
		contact.setName(name);
		contact.setAge(age);
		contact.setTel(tel);
		
		String userUuid = (String) request.getSession().getAttribute("userUuid");
		
		//3、调用service层
		boolean boo = service.addContact(userUuid, contact);
		
		//System.out.println(contact);
		
		request.getSession().setAttribute("id", uuid);
		//4、根据service层的返回结果，导向不同的结果界面
		if(boo){
			request.getRequestDispatcher("/jsps/saveback.jsp").forward(request, response);
		}else{
			//未添加成功的处理
			response.getWriter().print("<h1>很抱歉，没有添加成功！</h1>");
		}
	}
}
