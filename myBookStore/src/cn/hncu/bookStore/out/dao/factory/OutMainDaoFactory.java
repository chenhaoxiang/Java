package cn.hncu.bookStore.out.dao.factory;

import cn.hncu.bookStore.out.dao.dao.OutMainDao;
import cn.hncu.bookStore.out.dao.impl.OutMainDaoSerImpl;

/**
 * 工厂方法---new 一个销售实现类
 * @author 陈浩翔
 * @version 1.0
 */
public class OutMainDaoFactory {
	
	public static OutMainDao getOutMainDao(){
		return new OutMainDaoSerImpl();
	}
}
