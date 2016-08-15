package cn.hncu.stud.dao;

import java.sql.SQLException;
import java.util.List;

import cn.hncu.domain.Book;

public interface IBookDAO {
	public void save( List<Book> books ) throws InterruptedException, SQLException;
}
