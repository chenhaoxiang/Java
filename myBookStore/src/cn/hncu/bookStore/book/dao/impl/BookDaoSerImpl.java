package cn.hncu.bookStore.book.dao.impl;

import java.util.ArrayList;
import java.util.List;

import cn.hncu.bookStore.book.dao.dao.BookDao;
import cn.hncu.bookStore.book.vo.BookModel;
import cn.hncu.bookStore.book.vo.BookQueryModel;
import cn.hncu.bookStore.util.FileIoUtil;
import cn.hncu.bookStore.util.StringComparison;
/**
 * Dao µœ÷¿‡
 * @author ≥¬∫∆œË
 *
 * @version 1.0
 */
public class BookDaoSerImpl implements BookDao {
	private final String FILE_NAME = "Book.txt";

	@Override
	public boolean create(BookModel book) {
		List<BookModel> books = FileIoUtil.readFormFile(FILE_NAME);

		for (BookModel model : books) {
			if (model.getUuid().equals(book.getUuid())) {
				return false;
			}
		}
		books.add(book);
		FileIoUtil.write2file(books, FILE_NAME);
		return true;
	}

	@Override
	public boolean delete(String uuid) {
		List<BookModel> books = FileIoUtil.readFormFile(FILE_NAME);

		for (BookModel model : books) {
			if (model.getUuid().equals(uuid)) {
				books.remove(model);
				FileIoUtil.write2file(books, FILE_NAME);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean update(BookModel book) {
		List<BookModel> books = FileIoUtil.readFormFile(FILE_NAME);

		for (int i=0;i<books.size();i++) {
			if (books.get(i).getUuid().equals(book.getUuid())) {
				books.set(i, book);
				FileIoUtil.write2file(books, FILE_NAME);
				return true;
			}
		}
		
		return false;
	}

	@Override
	public List<BookModel> getAll() {
		return FileIoUtil.readFormFile(FILE_NAME);
	}

	@Override
	public List<BookModel> getbyCondition(BookQueryModel bqm) {
		List<BookModel> lists = getAll();
		List<BookModel> results = new ArrayList<BookModel>();
		
		for(BookModel book: lists){
			
			if(!StringComparison.stringEquals(book.getUuid(),bqm.getUuid())){
				continue;
			}
			
			if(!StringComparison.stringIndexOf( book.getName(),bqm.getName())){
				continue;
			}
			
			if(bqm.getInPrice()>0){
				if(book.getInPrice()<bqm.getInPrice()){
					continue;
				}
			}
			if(bqm.getInPrice2()>0){
				if(book.getInPrice()>bqm.getInPrice2()){
					continue;
				}
			}
			
			if(bqm.getSalePrice()>0){
				if(book.getSalePrice()<bqm.getSalePrice()){
					continue;
				}
			}
			if(bqm.getSalePrice2()>0){
				if(book.getSalePrice()>bqm.getSalePrice2()){
					continue;
				}
			}
			
			results.add(book);
		}
		
		return results;
	}

	@Override
	public BookModel getSingle(String uuid) {
		List<BookModel> list = FileIoUtil.readFormFile(FILE_NAME);
		for(BookModel book:list){
			if(book.getUuid().equals(uuid)){
				return book;
			}
		}
		return null;
	}

}
