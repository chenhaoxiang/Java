package cn.hncu.bookStore.out.dao.factory;

import cn.hncu.bookStore.out.dao.dao.OutDetailDao;
import cn.hncu.bookStore.out.dao.impl.OutDetailDaoSerImpl;

/**
 * 工厂方法----new 一个销售明细的实现类
 * @author 陈浩翔
 *
 * @version 1.0
 */
public class OutDetailDaoFactory {
	
	public static OutDetailDao getOutDetailDao(){
		return new OutDetailDaoSerImpl();
	}
}
