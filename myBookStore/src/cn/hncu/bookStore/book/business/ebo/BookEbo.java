package cn.hncu.bookStore.book.business.ebo;

import java.util.List;

import cn.hncu.bookStore.book.business.ebi.BookEbi;
import cn.hncu.bookStore.book.dao.dao.BookDao;
import cn.hncu.bookStore.book.dao.factory.BookDaoFactory;
import cn.hncu.bookStore.book.vo.BookModel;
import cn.hncu.bookStore.book.vo.BookQueryModel;
import cn.hncu.bookStore.common.UuidModelConstance;
import cn.hncu.bookStore.common.uuid.dao.factory.UuidDaoFactory;
import cn.hncu.bookStore.common.uuid.dao.impl.UuidDaoSerImpl;
/**
 * 实现类
 * @author 陈浩翔
 *
 * @version 1.0
 */
public class BookEbo implements BookEbi {
	//注入
	BookDao dao = BookDaoFactory.getBookDao();
	
	@Override
	public boolean create(BookModel book) {
		//如果表现层中对book对象的数据没有封装完整，那么在这里要为它补全
		//调用uuid模块的dao层来自动获取当前book对象的uuid
		String uuid = UuidDaoFactory.getUuidDao().getNextUuid(UuidModelConstance.BOOK);
		book.setUuid(uuid);
		return dao.create(book);
	}

	@Override
	public boolean delete(String uuid) {
		return dao.delete(uuid);
	}

	@Override
	public boolean update(BookModel book) {
		return dao.update(book);
	}

	@Override
	public List<BookModel> getAll() {
		return dao.getAll();
	}

	@Override
	public List<BookModel> getbyCondition(BookQueryModel bqm) {
		return dao.getbyCondition(bqm);
	}

	@Override
	public BookModel getSingle(String uuid) {
		return dao.getSingle(uuid);
	}

	@Override
	public BookModel getBookByName(String bookName) {
		BookQueryModel bqm = new BookQueryModel();
		bqm.setName(bookName);
		return getbyCondition(bqm).get(0);
	}

}
