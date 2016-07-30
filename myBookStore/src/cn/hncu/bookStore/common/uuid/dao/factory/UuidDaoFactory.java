package cn.hncu.bookStore.common.uuid.dao.factory;

import cn.hncu.bookStore.common.uuid.dao.dao.UuidDao;
import cn.hncu.bookStore.common.uuid.dao.impl.UuidDaoSerImpl;

/**
 * 工厂方法
 * @author 陈浩翔
 * @version 1.0
 */
public class UuidDaoFactory {
	/**
	 * 
	 * @return new一个UuidDao的具体实现类
	 */
	public static UuidDao getUuidDao(){
		return new UuidDaoSerImpl();
	}
}
