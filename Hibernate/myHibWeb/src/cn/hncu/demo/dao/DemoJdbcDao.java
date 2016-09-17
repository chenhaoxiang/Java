package cn.hncu.demo.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.hncu.domain.Student;
import cn.hncu.hib.HibernateSessionFactory;

public class DemoJdbcDao {
	public List<Student> queryAllStudents() {
		Session s = HibernateSessionFactory.getSession();
		Query query = s.createQuery("from Student");
		List<Student> list = query.list();
		return list;
	}

	public void delStudent(Student stud) {
		Session s = HibernateSessionFactory.getSession();
			try {
				Transaction tran = s.beginTransaction();
				System.out.println("stud:"+stud);
				s.delete(stud);
//				Student stud2 = new Student();
//				stud2.setStudId("S001");
//				s.save(stud2);
				tran.commit();
			} catch (HibernateException e) {
				//tran.rollback();//可以不写，内部会进行回滚
				System.out.println("抓到异常...");
			}
		
	}
	
	public void addStudent(Student stud) {
		Session s = HibernateSessionFactory.getSession();
		Transaction tran = s.beginTransaction();
		try {
			s.saveOrUpdate(stud);
			tran.commit();
		} catch (HibernateException e) {
		}
	}

	public List<Student> queryStudents(Student stud) {
		System.out.println(stud);
		boolean f1=false,f2=false,f3=false;
		Session s = HibernateSessionFactory.getSession();
		String hql = "from Student s where 1=1";
		
		if(stud.getsId()!=null && stud.getsId().trim().length()>0){
			hql = hql + " and s.sId=:sId";
			f1=true;
		}
		if(stud.getsName()!=null && stud.getsName().trim().length()>0){
			hql = hql + " and s.sName like :sName";
			f2=true;
		}
		if(stud.getDeptId()!=null && stud.getDeptId().trim().length()>0){
			hql = hql + " and s.deptId=:deptId";
			f3=true;
		}
		
		Query query = s.createQuery(hql);
		if(f1){
			query.setParameter("sId", stud.getsId().trim());
		}
		if(f2){
			query.setParameter("sName", "%"+stud.getsName().trim()+"%");
		}
		if(f3){
			query.setParameter("deptId", stud.getDeptId().trim());
		}
		return query.list();
	}
	
}
