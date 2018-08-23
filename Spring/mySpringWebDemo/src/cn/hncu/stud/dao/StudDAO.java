package cn.hncu.stud.dao;

import java.sql.SQLException;

import cn.hncu.stud.domain.Book;
import cn.hncu.stud.domain.Stud;

public interface StudDAO {
	public void saveStud(Stud stud)  throws SQLException;
}
