package cn.hncu;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import cn.hncu.domain.Student;
import cn.hncu.hib.HibernateSessionFactory;

public class TestHib {

	public static void main(String[] args) {
		/*创建一个配置对象，读取配置文件*/
		String configfile="/hibernate.cfg.xml";
		Configuration config=new Configuration();
		config.configure(configfile);
		/*通过配置对象产生一个会话工厂类*/
	    SessionFactory sessionfactory=config.buildSessionFactory();
		/*通过会话工厂类产生一个会话实例*/
		Session session=sessionfactory.openSession();
		/*通过会话产生一个查询对象Query*/
		Query query=session.createQuery("from Student");
		/*进行查询返回一个集合List*/
		List<Student> studs=query.list();
		for(Student s:studs){
		   System.out.println(s);
		}
		
		session = HibernateSessionFactory.getSession();
		query = session.createQuery("from Student");
		List<Student> students = query.list();
		for(Student s: students){
			System.out.println(s);
		}
	}
}
