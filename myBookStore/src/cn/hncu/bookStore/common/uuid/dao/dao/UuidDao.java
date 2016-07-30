package cn.hncu.bookStore.common.uuid.dao.dao;

import cn.hncu.bookStore.common.UuidModelConstance;

/**
 * Uuid模块的数据接口
 * @author 陈浩翔
 * @version 1.0
 */
public interface UuidDao {
	/**
	 * 
	 * @param uuidEnum---传入的模块名称
	 * @return ---根据传入的模块名称来分别返回下一个内部生成的Uuid
	 */
	public String getNextUuid(UuidModelConstance uuidEnum);
}
