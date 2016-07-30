package cn.hncu.bookStore.in.dao.factory;

import cn.hncu.bookStore.in.dao.dao.InMainDao;
import cn.hncu.bookStore.in.dao.impl.InMainDaoSerImpl;

/**
 * 工厂方法---new 一个进货实现类
 * @author 陈浩翔
 * @version 1.0
 */
public class InMainDaoFactory {
	
	public static InMainDao getInMainDao(){
		return new InMainDaoSerImpl();
	}
}
