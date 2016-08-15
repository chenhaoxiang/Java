package cn.hncu.stud;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hncu.domain.Book;
import cn.hncu.domain.Stud;
import cn.hncu.pubs.BaseServlet;
import cn.hncu.pubs.tx.TxProxy;
import cn.hncu.stud.service.IStudService;
import cn.hncu.stud.service.StudService;

public class StudServlet extends BaseServlet {
	//版本1
	//private IStudService service = (IStudService) TxProxy.getProxy(new StudService());
	//版本2
	//private IStudService service = TxProxy.getProxy(new StudService(),IStudService.class);
	//版本3
	//private IStudService service = TxProxy.getProxy(StudService.class);//用实现类的class
	//版本4
	private IStudService service = TxProxy.getProxy(new StudService());//用实现类的class
	
	private void save(HttpServletRequest request, HttpServletResponse response) throws InterruptedException, ServletException, IOException {
		//收集并封装stud
		String name = request.getParameter("name");
		if(name==null||name.trim().length()<=0){
			response.sendRedirect("index.jsp");//重定向
			return ;
		}
		
		Stud stud = new Stud();
		stud.setName(name);
		
		//收集并封装所有book
		String bookNames[] = request.getParameterValues("bookname");//获取多个值
		String prices[] = request.getParameterValues("price");
		
		if(bookNames!=null){
			for(int i=0;i<bookNames.length;i++){
				Book book = new Book();
				if(bookNames[i]==null || bookNames[i].trim().length()<=0){
					continue;
				}
				book.setNamem(bookNames[i]);
				if(prices[i]!=null && prices[i].trim().length()!=0 ){
					Double price=0.0;
					try {
						price = Double.parseDouble(prices[i]);
					} catch (NumberFormatException e) {
						price=0.0;
					}
					book.setPrice(price);
				}
				book.setStud(stud);
				//继续封装stud----为stud中的books属性赋值--一方中的集合
				stud.getBooks().add(book);
			}
		}
		try {
			service.save(stud);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Map<String, String>> studs = service.query();
		request.setAttribute("studs", studs);
		request.getRequestDispatcher("/jsps/show.jsp").forward(request, response);
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("调用默认业务处理方法....");
	}
}
