package cn.hncu.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hncu.service.IPageService;
import cn.hncu.service.PageServiceImpl;

public class PageServlet extends HttpServlet {
	//注入service
		IPageService service = new PageServiceImpl();
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			doPost(req, resp);
		}

		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
	String pageNo = request.getParameter("page");
			
			if(pageNo==null || pageNo.trim().length()<=0){
				pageNo="1";
			}
			
			Integer iPageNo=1;
			try {
				iPageNo = Integer.parseInt(pageNo);
			} catch (NumberFormatException e) {
				iPageNo=1;
			}
			
			try {
				Map<String, Object> result = service.query(iPageNo);
				
				//给结果集补一个数据:currentPage
				result.put("currentPage", iPageNo);
				
				//###注意，一定要把结果集放入容器中
				request.setAttribute("result", result);
				
				//转到结果页面
				request.getRequestDispatcher("/jsps/show.jsp").forward(request, response);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}
