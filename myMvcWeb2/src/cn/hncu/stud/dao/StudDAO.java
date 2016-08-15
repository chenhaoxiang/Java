package cn.hncu.stud.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.hncu.domain.Stud;

public interface StudDAO {
	public abstract List< Map<String, String> > query();
	public abstract void save(Stud stud) throws InterruptedException, SQLException;
}
