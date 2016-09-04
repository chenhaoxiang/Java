package cn.hncu.stud.dao;

import java.sql.SQLException;

import cn.hncu.stud.domain.Book;

public interface BookDAO {
	public void saveBook(Book book)  throws SQLException;
}
