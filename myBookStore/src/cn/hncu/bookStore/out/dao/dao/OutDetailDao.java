package cn.hncu.bookStore.out.dao.dao;

import java.util.List;

import cn.hncu.bookStore.out.vo.OutDetailModel;
import cn.hncu.bookStore.out.vo.OutDetailQueryModel;

/**
 * 销售明细管理数据层接口
 * @author 陈浩翔
 *
 * @version 1.0
 */
public interface OutDetailDao {
	/**
	 * 创建一个销售明细数据对象
	 * @param outMain---需要创建的销售明细数据对象
	 * @return---true 表示创建成功，false 表示创建失败
	 */
	public boolean create(OutDetailModel outDetail);
	
	/**
	 * 删除一个销售明细数据对象
	 * @param uuid---销售明细单的编号-唯一的
	 * @return---true表示删除成功， false表示删除失败
	 */
	public boolean delete(String uuid);
	
	/**
	 * 修改一个销售明细数据的对象
	 * @param outMain---需要修改的销售管理明细对象
	 * @return---true表示修改成功，false表示修改失败
	 */
	public boolean update(OutDetailModel outDetail);
	
	/**
	 * 根据销售明细单编号，得到销售明细对象的其他信息资料
	 * @param uuid---销售明细单编号
	 * @return---该对象的其他其他信息资料
	 */
	public OutDetailModel getSingle(String uuid);
	
	/**
	 * 
	 * @return---所有的销售明细管理对象信息
	 */
	public List<OutDetailModel> getAll();
	
	/**
	 * 查找销售明细管理对象
	 * @param omqm---查找条件！
	 * @return---满足查找条件的所有OutDetailModel对象
	 */
	public List<OutDetailModel> getbyCondition(OutDetailQueryModel odqm);
}
