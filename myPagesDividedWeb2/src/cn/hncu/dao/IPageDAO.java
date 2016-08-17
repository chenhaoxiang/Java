package cn.hncu.dao;

import java.sql.SQLException;
import java.util.Map;

import cn.hncu.domain.Person;

public interface IPageDAO {
	public Map<String, Object> query(Integer pageNo,Person person) throws NumberFormatException, SQLException;
}
