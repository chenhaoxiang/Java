package cn.hncu.bookStore.in.dao.dao;

import java.util.List;

import cn.hncu.bookStore.in.vo.InMainModel;
import cn.hncu.bookStore.in.vo.InMainQueryModel;

/**
 * 进货管理数据层接口
 * 
 * @author 陈浩翔
 * 
 * @version 1.0
 */
public interface InMainDao {
	
	/**
	 * 创建一个进货数据对象
	 * @param inMain---需要创建的进货数据对象
	 * @return---true 表示创建成功，false 表示创建失败
	 */
	public boolean create(InMainModel inMain);
	
	/**
	 * 删除一个进货数据对象
	 * @param uuid---进货单的编号-唯一的
	 * @return---true表示删除成功， false表示删除失败
	 */
	public boolean delete(String uuid);
	
	/**
	 * 修改一个进货数据的对象
	 * @param inMain---需要修改的进货管理对象
	 * @return---true表示修改成功，false表示修改失败
	 */
	public boolean update(InMainModel inMain);
	
	/**
	 * 根据进货单编号，得到进货对象的其他信息资料
	 * @param uuid---进货单编号
	 * @return---该对象的其他其他信息资料
	 */
	public InMainModel getSingle(String uuid);
	
	/**
	 * 
	 * @return---所有的进货管理对象信息
	 */
	public List<InMainModel> getAll();
	
	/**
	 * 查找进货管理对象
	 * @param imqm---查找条件！
	 * @return---满足查找条件的所有INMainModel对象
	 */
	public List<InMainModel> getbyCondition(InMainQueryModel imqm);
}
