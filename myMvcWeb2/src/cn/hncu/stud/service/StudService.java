package cn.hncu.stud.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.hncu.domain.Stud;
import cn.hncu.stud.dao.BookDAOJdbc;
import cn.hncu.stud.dao.IBookDAO;
import cn.hncu.stud.dao.StudDAO;
import cn.hncu.stud.dao.StudDAOJdbc;

public class StudService implements IStudService {

	//注入
	private StudDAO studDao = new StudDAOJdbc();
	private IBookDAO bookDao = new BookDAOJdbc();
	@Override
	public List<Map<String, String>> query() {
		return studDao.query();
	}
	
	//※利用动态代理在背后帮忙实现事务功能，注意：该方法内部的异常必须抛出来给动态代理捕捉处理
	@Override
	public void save(Stud stud) throws InterruptedException, SQLException {
		studDao.save(stud);//在这个内部为stud对象补id--先执行
		bookDao.save(stud.getBooks());//通过book拿到stud对象，进而拿到studid,完成外键字段的赋值
	}

}
