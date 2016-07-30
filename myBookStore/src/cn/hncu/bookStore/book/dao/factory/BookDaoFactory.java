package cn.hncu.bookStore.book.dao.factory;

import cn.hncu.bookStore.book.dao.dao.BookDao;
import cn.hncu.bookStore.book.dao.impl.BookDaoSerImpl;
/**
 * 工厂方法
 * new一个实例
 * @author 陈浩翔
 * @version 1.0
 */
public class BookDaoFactory {
	/**
	 * 
	 * @return BookDao接口的一个实例
	 */
	public static BookDao getBookDao(){
		return new BookDaoSerImpl();
	}
}
