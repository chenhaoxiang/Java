package cn.hncu.bookStore.in.dao.dao;

import java.util.List;

import cn.hncu.bookStore.in.vo.InDetailModel;
import cn.hncu.bookStore.in.vo.InDetailQueryModel;

/**
 * 进货明细管理数据层接口
 * @author 陈浩翔
 *
 * @version 1.0
 */
public interface InDetailDao {
	/**
	 * 创建一个进货明细数据对象
	 * @param inMain---需要创建的进货明细数据对象
	 * @return---true 表示创建成功，false 表示创建失败
	 */
	public boolean create(InDetailModel inDetail);
	
	/**
	 * 删除一个进货明细数据对象
	 * @param uuid---进货明细单的编号-唯一的
	 * @return---true表示删除成功， false表示删除失败
	 */
	public boolean delete(String uuid);
	
	/**
	 * 修改一个进货明细数据的对象
	 * @param inMain---需要修改的进货管理明细对象
	 * @return---true表示修改成功，false表示修改失败
	 */
	public boolean update(InDetailModel inDetail);
	
	/**
	 * 根据进货明细单编号，得到进货明细对象的其他信息资料
	 * @param uuid---进货明细单编号
	 * @return---该对象的其他其他信息资料
	 */
	public InDetailModel getSingle(String uuid);
	
	/**
	 * 
	 * @return---所有的进货明细管理对象信息
	 */
	public List<InDetailModel> getAll();
	
	/**
	 * 查找进货明细管理对象
	 * @param imqm---查找条件！
	 * @return---满足查找条件的所有InDetailModel对象
	 */
	public List<InDetailModel> getbyCondition(InDetailQueryModel idqm);
}
