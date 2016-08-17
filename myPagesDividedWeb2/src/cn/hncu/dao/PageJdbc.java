package cn.hncu.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.hncu.domain.Person;
import cn.hncu.pubs.C3p0Pool;

public class PageJdbc implements IPageDAO {
	private final int pageSize = 10;// 每页显示的行数

	@Override
	public Map<String, Object> query(Integer pageNo, Person person)
			throws NumberFormatException, SQLException {
		Map<String, Object> result = new HashMap<String, Object>();

		// 获取总页数 pageCount = rows/pageSize + ( (rows%pageSize==0)?0:1 )
		// 总行数rows
		String sql = "select count(1) from person where 1=1 ";// 用来查总行数

		String sql2 = "select * from person where 1=1 ";// 用来查表内容

		if (person.getId() != null && person.getId().trim().length() != 0) {
			sql = sql + "and id like '%" + person.getId() + "%'";
			sql2 = sql2 + "and id like '%" + person.getId() + "%'";
		}
		if (person.getName() != null && person.getName().trim().length() != 0) {
			sql = sql + "and name like '%" + person.getName() + "%'";
			sql2 = sql2 + "and name like '%" + person.getName() + "%'";
		}
		//System.out.println(sql2);
		
		QueryRunner run = new QueryRunner(C3p0Pool.getDataSource());
		int rows = Integer.parseInt("" + run.query(sql, new ScalarHandler()));

		// 总页数
		int pageCount = rows / pageSize + ((rows % pageSize == 0) ? 0 : 1);
		result.put("pageCount", pageCount);// 封装到result
		// 分页页面内容datas
		// 起始行号
		int startN = (pageNo - 1) * pageSize;
		sql2 = sql2+ " limit "+startN+", "+pageSize;
		
		List<Map<String, Object>> datas = run.query(sql2, new MapListHandler());
		result.put("datas", datas);//封装到result
			
		return result;
	}

}
