package cn.hncu.stud.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.hncu.stud.domain.Book;
import cn.hncu.stud.domain.Stud;
import cn.hncu.stud.service.ISaveService;

public class StudServlet extends HttpServlet {
	//依赖注入
	private ISaveService service =null;
	
	@Override
	public void init() throws ServletException {
		//在这里，我们可以直接获取Web中的Spring容器-不能重新去new，因为那样就不是同一个容器的了
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		service=ctx.getBean("saveService",ISaveService.class);
		System.err.println("service:"+service);
	}
	public ISaveService getService() {
		return service;
	}
	public void setService(ISaveService service) {
		this.service = service;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//应该在过滤器中设置编码
		String studName = request.getParameter("studName");
		Stud stud = new Stud();
		stud.setS_name(studName);
		
		String bookName = request.getParameter("bookName");
		Book book = new Book();
		book.setB_name(bookName);
		try {
			service.saveStudAndBook(stud, book);
		} catch (SQLException e) {
			//如果有异常，应该返回到专门的处理异常的页面去，返回用户能够看懂的信息
			e.printStackTrace();
		}
	}

}
