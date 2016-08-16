package cn.hncu.dao;

import java.sql.SQLException;
import java.util.Map;

public interface IPageDAO {
	public Map<String, Object> query(Integer pageNo) throws NumberFormatException, SQLException;
}
