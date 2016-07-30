package cn.hncu.bookStore.user.dao.factory;

import cn.hncu.bookStore.user.dao.dao.UserDao;
import cn.hncu.bookStore.user.dao.impl.UserDaoSerImpl;
/**
 * 工厂方法<br/>
 * new 一个dao的实例
 * @author 陈浩翔
 *
 * @version 1.0
 * 
 */
public class UserDaoFactory {
	public static UserDao getUserDao(){
		return new UserDaoSerImpl();
	}
}
