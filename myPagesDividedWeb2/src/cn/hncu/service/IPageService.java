package cn.hncu.service;

import java.sql.SQLException;
import java.util.Map;

import cn.hncu.domain.Person;

public interface IPageService {
	public Map<String, Object> query(Integer pageNo,Person person) throws NumberFormatException, SQLException;
}
