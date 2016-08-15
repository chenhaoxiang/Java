package cn.hncu.stud.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.hncu.domain.Stud;
import cn.hncu.pubs.tx.Transaction;

public interface IStudService {
	public abstract List< Map<String, String> > query();
	
	@Transaction//注意，注解必须加在接口上，加在实现类上是无效的!,因为我们的动态代理是面向接口的
	public abstract void save(Stud stud) throws InterruptedException, SQLException;
}
