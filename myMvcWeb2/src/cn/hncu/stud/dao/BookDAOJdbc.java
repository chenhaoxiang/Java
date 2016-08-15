package cn.hncu.stud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cn.hncu.domain.Book;
import cn.hncu.pubs.ConnsUtil;

public class BookDAOJdbc implements IBookDAO {

	@Override
	public void save(List<Book> books) throws InterruptedException, SQLException {
		Connection con = ConnsUtil.getConnection();
		
		String sql = "insert into book(name,price,studid) values(?,?,?)";
		
		PreparedStatement pst = con.prepareStatement(sql);
		for(Book book:books){
			pst.setString(1, book.getNamem());
			pst.setDouble(2, book.getPrice());
			//stud中设置了uuid这里才能设置成功
			pst.setString(3, book.getStud().getId());
			pst.addBatch();
		}
		pst.executeBatch();
	}
}
