package cn.hncu.stud.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import cn.hncu.stud.domain.Book;

public class BookDaoJdbc implements BookDAO{
	private DataSource dataSource = null;//依赖注入

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void saveBook(Book book) throws SQLException {
		QueryRunner run = new QueryRunner(dataSource);
		//run.update("insert into book(b_name) values(?)", book.getB_name());
		//演示事务回滚
		run.update("insert into bme) values(?)", book.getB_name());
	}

}
