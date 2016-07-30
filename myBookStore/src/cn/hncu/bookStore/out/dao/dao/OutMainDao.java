package cn.hncu.bookStore.out.dao.dao;

import java.util.List;

import cn.hncu.bookStore.out.vo.OutMainModel;
import cn.hncu.bookStore.out.vo.OutMainQueryModel;

/**
 * 销售管理数据层接口
 * 
 * @author 陈浩翔
 * 
 * @version 1.0
 */
public interface OutMainDao {
	
	/**
	 * 创建一个销售数据对象
	 * @param outMain---需要创建的销售数据对象
	 * @return---true 表示创建成功，false 表示创建失败
	 */
	public boolean create(OutMainModel outMain);
	
	/**
	 * 删除一个销售数据对象
	 * @param uuid---销售单的编号-唯一的
	 * @return---true表示删除成功， false表示删除失败
	 */
	public boolean delete(String uuid);
	
	/**
	 * 修改一个销售数据的对象
	 * @param outMain---需要修改的销售管理对象
	 * @return---true表示修改成功，false表示修改失败
	 */
	public boolean update(OutMainModel outMain);
	
	/**
	 * 根据销售单编号，得到销售对象的其他信息资料
	 * @param uuid---销售单编号
	 * @return---该对象的其他其他信息资料
	 */
	public OutMainModel getSingle(String uuid);
	
	/**
	 * 
	 * @return---所有的销售管理对象信息
	 */
	public List<OutMainModel> getAll();
	
	/**
	 * 查找销售管理对象
	 * @param imqm---查找条件！
	 * @return---满足查找条件的所有OutMainModel对象
	 */
	public List<OutMainModel> getbyCondition(OutMainQueryModel omqm);
}
