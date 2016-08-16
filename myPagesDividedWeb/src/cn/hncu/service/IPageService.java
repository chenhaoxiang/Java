package cn.hncu.service;

import java.sql.SQLException;
import java.util.Map;

public interface IPageService {
	public Map<String, Object> query(Integer pageNo) throws NumberFormatException, SQLException;
}
