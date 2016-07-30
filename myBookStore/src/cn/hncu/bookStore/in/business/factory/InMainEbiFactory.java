package cn.hncu.bookStore.in.business.factory;

import cn.hncu.bookStore.in.business.ebi.InMainEbi;
import cn.hncu.bookStore.in.business.ebo.InMainEbo;

/**
 * 工厂方法
 * @author 陈浩翔
 *
 * @version 1.0
 */
public class InMainEbiFactory {
	/**
	 * 
	 * @return---new一个逻辑层的接口的实例
	 */
	public static InMainEbi getInMainEbi(){
		return new InMainEbo();
	}
}
