package cn.hncu.bookStore.in.dao.factory;

import cn.hncu.bookStore.in.dao.dao.InDetailDao;
import cn.hncu.bookStore.in.dao.impl.InDetailDaoSerImpl;

/**
 * 工厂方法----new 一个进货明细的实现类
 * @author 陈浩翔
 *
 * @version 1.0
 */
public class InDetailDaoFactory {
	
	public static InDetailDao getInDetailDao(){
		return new InDetailDaoSerImpl();
	}
}
