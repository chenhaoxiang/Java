package cn.hncu.bookStore.book.business.factory;

import cn.hncu.bookStore.book.business.ebi.BookEbi;
import cn.hncu.bookStore.book.business.ebo.BookEbo;
/**
 * 工厂方法
 *new 一个实例
 * @author 陈浩翔
 * @version 1.0
 */
public class BookEbiFactory {
	
	public static BookEbi getBookEbi(){
		return new BookEbo();
	}
}
