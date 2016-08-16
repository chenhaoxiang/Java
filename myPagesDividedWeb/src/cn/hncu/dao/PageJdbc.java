package cn.hncu.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import cn.hncu.pubs.C3p0Pool;

public class PageJdbc implements IPageDAO {
	
	//每页显示的行数
	private final int pageSize = 10;
	
	@Override
	public Map<String, Object> query(Integer pageNo) throws NumberFormatException, SQLException {
		Map<String, Object> result = new HashMap<String, Object>();
		
		//获取总页数 pageCount = rows/pageSize + ((rows%pageSize==0)?0:1)
		//总行数 rows
		String sql = "select count(1) from person";
		QueryRunner run = new QueryRunner(C3p0Pool.getDataSource());
		
		int rows =Integer.parseInt(""+run.query(sql, new ScalarHandler())); 
		
		//总页数
		int pageCount = rows/pageSize +  ((rows%pageSize==0)?0:1);
		result.put("pageCount", pageCount);
		
		//分页后的当前页面内容datas
		//起始行号
		int startN = (pageNo-1)*pageSize;
		sql = "select * from person limit "+startN+" , "+pageSize;
		List<Map<String, Object>> datas = run.query(sql, new MapListHandler());
		result.put("datas", datas);//封装到result
		
		return result;
	}
	
	@Test
	public void test() {
		try {
			Map<String, Object> map = query(5);
			System.out.println(map);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
