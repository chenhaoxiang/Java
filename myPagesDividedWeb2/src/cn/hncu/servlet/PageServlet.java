package cn.hncu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hncu.domain.Person;
import cn.hncu.service.IPageService;
import cn.hncu.service.PageServiceImpl;

public class PageServlet extends HttpServlet {
	//注入servlet
	IPageService service = new PageServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String pageNo = request.getParameter("page");
		if(pageNo==null || pageNo.trim().length()==0){
			pageNo = "1";
		}
		Integer iPageNo =1;
		try {
			iPageNo = Integer.parseInt(pageNo);
		} catch (NumberFormatException e) {
			request.getRequestDispatcher(request.getContextPath()+"/index.jsp").forward(request, response);
		}
		
		Person person =null;
		//区分是Post还是GET方式请求，前者是新的查询，后者是原查询结果中进行翻页
		if(request.getMethod().equalsIgnoreCase("get")){//翻页
			//从session中把旧的查询条件值对象取出来
			person = (Person) request.getSession().getAttribute("person");
			if(person==null){//没有进行第一次查询之前，person就是需要我们new出来一个空的
				person = new Person();
			}
		}else{//POST方式---新的查询
			//收集模糊查询的输入参数
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			person = new Person();
			person.setId(id);
			person.setName(name);
			//放入session,这样下一次翻页才能读取出来
			request.getSession().setAttribute("person", person);
		}
		
		
		try {
			Map<String, Object> result = service.query(iPageNo, person);
			
			//给结果集补一个数据:currentPage --当前页号
			result.put("currentPage", iPageNo);
			
			//把结果集放入容器中
			request.setAttribute("result", result);
			
			//分页后的分页--向前端多传送两个数据(起始和末尾页号)：showStart和showEnd
			int showSize=10;//每页显示几行
			int showStart=0;//从第几个页号开始显示
			int showEnd=0;//显示到第几个页号
			
			//pageCount---总页数
			//如果所有页码不足showSize:  showStart=1,showEnd=pageCount
			int pageCount = Integer.parseInt(""+result.get("pageCount"));
			
			if(pageCount<=showSize){//不需要二次分页
				showStart=1;
				showEnd=pageCount;
			}else{//需要二次分页
				//如果当前页号iPageNo<=showSize/2时，showStart=1
				if(iPageNo<=showSize/2){
					showStart=1;
					showEnd=showSize;
				}else{
					showStart = iPageNo - showSize/2;
					showEnd = showStart + showSize-1;
				}
				
				if(showEnd>pageCount){
					showEnd=pageCount;
					showStart = showEnd-showSize-1;
				}
				
			}
			
			//存储showStart和showEnd
			request.setAttribute("showStart", showStart);
			request.setAttribute("showEnd", showEnd);
			
			//转到结果页面
			request.getRequestDispatcher("/jsps/show.jsp").forward(request, response);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
