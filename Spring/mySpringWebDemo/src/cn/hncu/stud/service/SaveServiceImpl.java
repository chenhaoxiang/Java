package cn.hncu.stud.service;

import java.sql.SQLException;

import cn.hncu.stud.dao.BookDAO;
import cn.hncu.stud.dao.BookDaoJdbc;
import cn.hncu.stud.dao.StudDAO;
import cn.hncu.stud.domain.Book;
import cn.hncu.stud.domain.Stud;

public class SaveServiceImpl implements ISaveService{
	//依赖注入
	private StudDAO studDao = null;
	private BookDAO bookDao = null;
	
	//必须写好set-get方法
	public StudDAO getStudDao() {
		return studDao;
	}
	public void setStudDao(StudDAO studDao) {
		this.studDao = studDao;
	}
	public BookDAO getBookDao() {
		return bookDao;
	}
	public void setBookDao(BookDAO bookDao) {
		this.bookDao = bookDao;
	}
	@Override
	public void saveStudAndBook(Stud stud, Book book) throws SQLException {
		studDao.saveStud(stud);
		bookDao.saveBook(book);
	}

}
