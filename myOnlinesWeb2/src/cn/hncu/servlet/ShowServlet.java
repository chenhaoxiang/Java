package cn.hncu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Map<String, HttpSession> onLines = (Map<String, HttpSession>) getServletContext().getAttribute("onLines"); 
		
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//遍历onlines，针对每个session对象封装一个map(信息包含:name,createTime,lastAccessTime,ip)，把该map添加到list当中
		Iterator<Entry<String, HttpSession>> it = onLines.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, HttpSession> en = it.next();
			
			Map<String, Object> m = new HashMap<String, Object>();
			
			m.put("id", en.getValue().getId() );
			m.put("user", en.getValue().getAttribute("user"));
			
			String creationTime = sdf.format(new Date(en.getValue().getCreationTime()));
			m.put("creationTime", creationTime);
			String lastAccessTime = sdf.format( new Date(en.getValue().getLastAccessedTime()) );
			m.put("lastAccessTime", lastAccessTime);
			
			 //注意,该ip数据是在CharsetFilter中封装的
			m.put("ip", en.getValue().getAttribute("ip"));
			
			lists.add(m);
		}
		
		request.setAttribute("onLines", lists);
		request.getRequestDispatcher("/jsps/show.jsp").forward(request, response);
	}
}
