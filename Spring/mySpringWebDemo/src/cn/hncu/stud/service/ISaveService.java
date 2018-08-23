package cn.hncu.stud.service;

import java.sql.SQLException;

import cn.hncu.stud.domain.Book;
import cn.hncu.stud.domain.Stud;

public interface ISaveService {
	public void saveStudAndBook(Stud stud,Book book)  throws SQLException;
}
