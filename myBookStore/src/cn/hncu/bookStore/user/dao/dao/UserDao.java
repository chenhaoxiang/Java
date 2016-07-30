package cn.hncu.bookStore.user.dao.dao;

import java.util.List;

import cn.hncu.bookStore.user.vo.UserModel;
import cn.hncu.bookStore.user.vo.UserQueryModel;

/**
 * 
 * @author 陈浩翔
 *
 * @version 1.0
 * 用户模块的数据层接口
 */
public interface UserDao {
	/**
	 * 功能：创建一个用户
	 * 
	 * @param userModel---将要创建的用户数据
	 * @return---true表示创建成功，false表示创建失败
	 */
	public boolean create(UserModel user);
	
	/**
	 * 功能：根据用户的唯一标识码uuid删除一个用户
	 * 
	 * @param uuid---用户唯一的标识码，每个用户都不会相同
	 * @return---true表示删除成功，false表示删除失败
	 */
	public boolean delete(String uuid);
	 
	/**
	 * 功能：修改用户的数据资料
	 * 
	 * @param user---需要修改的用户数据参数名
	 * @return 返回true-表示修改成功了，返回false-表示修改失败
	 */
	public boolean update(UserModel user);
	
	/**
	 * 功能：得到所有的用户数据
	 * 
	 * @return---一个UserModel集合，也就是用户的数据
	 */
	public List<UserModel> getAll();
	
	/**
	 * 功能：按照一定的查找条件进行查找，
	 * <br/>
	 * 把满足查找条件的用户数据返回。
	 * 
	 * @param uqm---被封装的查找条件
	 * @return---满足查找条件的用户数据集合
	 */
	public List<UserModel> getbyCondition(UserQueryModel uqm);
	
	/**
	 * 功能：得到一个确定的用户的数据资料
	 * 
	 * @param uuid---用户唯一标识码
	 * @return ---返回按这个唯一标识码找到的用户数据
	 */
	public UserModel getSingle(String uuid);
	
}
