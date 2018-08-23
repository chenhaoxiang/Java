package cn.hncu.demo.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.hncu.domain.Dept;
import cn.hncu.domain.Student;
import cn.hncu.hib.HibernateSessionFactory;

public class DemoJdbcDao {
	public Dept queryDeptById(Dept dept) {
		Session s = HibernateSessionFactory.getSession();
		String hql = "from Dept d where d.dId=?";
		//String hql = "from Dept";
		Query query = s.createQuery(hql);
		query.setParameter(0, dept.getdId());
		//根据部门ID去查的，只会有一个查询结果
		Dept resDept = (Dept) query.list().get(0);
		return resDept;
	}

	public void addDept(Dept dept) {
		Session s = HibernateSessionFactory.getSession();
		if(dept.getdName()==null){
			Query query = s.createQuery("from Dept d where d.dId=?");
			query.setParameter(0, dept.getdId());
			//对于学院存在的，如果没有填写学院名称，为其补上
			dept.setdName( ((Dept) query.list().get(0)).getdName() );
		}
		s.clear();//把之前的session信息清空，因为不允许一个session对象进行几个操作
		Transaction tx = s.beginTransaction();
		s.saveOrUpdate(dept);
		tx.commit();
	}
	
}
