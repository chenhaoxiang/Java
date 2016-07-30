package cn.hncu.bookStore.book.business.ebi;

import java.util.List;

import cn.hncu.bookStore.book.vo.BookModel;
import cn.hncu.bookStore.book.vo.BookQueryModel;

/**
 * 
 * @author 陈浩翔
 *
 * @version 1.0
 * 图书模块的逻辑层接口
 */
public interface BookEbi {
		
		/**
		 * 功能：创建一本图书
		 * 
		 * @param bookModel---将要创建的图书数据
		 * @return---true表示创建成功，false表示创建失败
		 */
		public boolean create(BookModel book);
		
		
		/**
		 * 功能：根据图书的唯一标识码uuid删除一本图书
		 * 
		 * @param uuid---图书唯一的标识码，每本图书都不会相同
		 * @return---true表示删除成功，false表示删除失败
		 */
		public boolean delete(String uuid);
		 
		
		/**
		 * 功能：修改图书的数据资料
		 * 
		 * @param user---需要修改的图书数据参数名
		 * @return 返回true-表示修改成功了，返回false-表示修改失败
		 */
		public boolean update(BookModel book);
		
		
		/**
		 * 功能：得到所有的图书数据
		 * 
		 * @return---一个BookModel集合，也就是图书的数据
		 */
		public List<BookModel> getAll();
		
		
		/**
		 * 功能：按照一定的查找条件进行查找，
		 * <br/>
		 * 把满足查找条件的图书数据返回。
		 * 
		 * @param bqm---被封装的查找条件
		 * @return---满足查找条件的图书数据集合
		 */
		public List<BookModel> getbyCondition(BookQueryModel bqm);
		
		
		/**
		 * 功能：得到一个确定的图书的数据资料
		 * 
		 * @param uuid---图书唯一标识码
		 * @return ---返回按这个唯一标识码找到的图书数据
		 */
		public BookModel getSingle(String uuid);
		
		/**
		 * 功能：根据书的名字得到书的uuid
		 * @param bookName---书的名字
		 * @return---返回书的uuid，如果没有找到那本书，返回null
		 */
		public BookModel getBookByName(String bookName);
		
	}
