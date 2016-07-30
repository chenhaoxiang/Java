package cn.hncu.bookStore.out.business.factory;

import cn.hncu.bookStore.out.business.ebi.OutMainEbi;
import cn.hncu.bookStore.out.business.ebo.OutMainEbo;

/**
 * 工厂方法
 * @author 陈浩翔
 *
 * @version 1.0
 */
public class OutMainEbiFactory {
	/**
	 * 
	 * @return---new一个逻辑层的接口的实例
	 */
	public static OutMainEbi getOutMainEbi(){
		return new OutMainEbo();
	}
}
