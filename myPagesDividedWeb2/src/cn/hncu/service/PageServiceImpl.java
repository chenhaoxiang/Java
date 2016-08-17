package cn.hncu.service;

import java.sql.SQLException;
import java.util.Map;

import cn.hncu.dao.IPageDAO;
import cn.hncu.dao.PageJdbc;
import cn.hncu.domain.Person;

public class PageServiceImpl implements IPageService{
	//注入dao
	IPageDAO dao = new PageJdbc();

	@Override
	public Map<String, Object> query(Integer pageNo, Person person) throws NumberFormatException, SQLException {
		return dao.query(pageNo, person);
	}
	
	
}
