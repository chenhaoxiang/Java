package cn.hncu.stud.dao;

import java.sql.SQLException;
import java.util.UUID;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import cn.hncu.stud.domain.Book;
import cn.hncu.stud.domain.Stud;

public class StudDaoJdbc implements StudDAO{
	private DataSource dataSource = null;//依赖注入
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void saveStud(Stud stud) throws SQLException {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		stud.setS_id(uuid);
		QueryRunner run = new QueryRunner(dataSource);
		run.update("insert into stud(s_id,s_name) values(?,?)", stud.getS_id(),stud.getS_name());
	}
}
