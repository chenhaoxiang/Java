package cn.hncu.hib;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
   private static String configFile = "/hibernate.cfg.xml";
   private static Configuration config = new Configuration();
   private static SessionFactory sessionFactory =null;
   
   private static final ThreadLocal<Session> t = new ThreadLocal<Session>();
   
   static{
	   try {
		   /*创建一个配置对象，读取配置文件*/
		   config.configure(configFile);
		   /*通过配置对象产生一个会话工厂类*/
		   sessionFactory = config.buildSessionFactory();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
   }
   
   public static Session getSession() throws HibernateException{
	   Session session = t.get();
	   if(session == null || !session.isOpen()){
		   if(sessionFactory==null){
			   rebuildSessionFactory();
		   }
		   /*通过会话工厂类产生一个会话实例*/
		   session = (sessionFactory!=null) ? sessionFactory.openSession() : null;
		   t.set(session);
	   }
	   return session;
   }

   private static void rebuildSessionFactory() {
	   try {
		   config.configure(configFile);
		   sessionFactory = config.buildSessionFactory();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
   }
   
   //关闭与数据库的会话
   public static void closeSession() throws HibernateException{
	   Session session = t.get();
	   t.set(null);
	   if(session!=null){
		   session.close();
	   }
   }
   
   
}
