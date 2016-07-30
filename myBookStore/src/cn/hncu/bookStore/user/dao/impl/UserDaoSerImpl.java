package cn.hncu.bookStore.user.dao.impl;

import java.util.ArrayList;
import java.util.List;

import cn.hncu.bookStore.common.UserTypeEnum;
import cn.hncu.bookStore.user.business.factory.UserEbiFactory;
import cn.hncu.bookStore.user.dao.dao.UserDao;
import cn.hncu.bookStore.user.vo.UserModel;
import cn.hncu.bookStore.user.vo.UserQueryModel;
import cn.hncu.bookStore.util.FileIoUtil;
import cn.hncu.bookStore.util.StringComparison;

/**
 * <br/>
 * 对用户数据处理的具体实现类 ----实现了UserDao接口
 * 
 * @author 陈浩翔
 * 
 * @version 1.0
 */
public class UserDaoSerImpl implements UserDao {

	private static final String FILE_NAME = "User.txt";

	@Override
	public boolean create(UserModel user) {
		// 1先把已有的数据反序列化(读)出来
		List<UserModel> list = FileIoUtil.readFormFile(FILE_NAME);
		// 2判断该用户是否已经存在，再决定是否创建
		for (UserModel userModel : list) {
			// 如果2个用户的uuid相等，用户就是相同的
			if (userModel.getUuid().equals(user.getUuid())) {
				return false;// 用户已经存在了，返回false
			}
		}
		// 3如果用户不存在，就创建
		list.add(user);
		FileIoUtil.write2file(list, FILE_NAME);
		return true;// 创建成功，返回true
	}

	@Override
	public boolean delete(String uuid) {

		// 1先把已有的数据反序列化(读)出来
		List<UserModel> list = FileIoUtil.readFormFile(FILE_NAME);

		// 2判断该用户是否已经存在，再决定是否删除

		// for(int i=0;i<list.size();i++){
		// if(list.get(i).getUuid().equals(uuid)){
		// list.remove(i);
		// FileIoUtil.write2file(list, FILE_NAME);
		// return true;
		// }
		// }

		for (UserModel userModel : list) {
			// 如果2个用户的uuid相等，用户就是相同的
			if (userModel.getUuid().equals(uuid)) {
				list.remove(userModel);
				FileIoUtil.write2file(list, FILE_NAME);
				// 删除成功，返回true
				return true;
			}
		}
		// 3用户不存在
		// 删除失败，返回false
		return false;
	}

	@Override
	public boolean update(UserModel user) {
		// 1先把已有的数据反序列化(读)出来
		List<UserModel> list = FileIoUtil.readFormFile(FILE_NAME);

		// 2判断该用户是否已经存在，再决定是否创建
		for (int i = 0; i < list.size(); i++) {
			// uuid是不能改的，通过uuid来找到那个用户数据，再修改就ok了
			if (list.get(i).getUuid().equals(user.getUuid())) {
				// 将找到的用户修改成user
				list.set(i, user);
				FileIoUtil.write2file(list, FILE_NAME);
				// 找到用户，返回true
				return true;
			}
		}
		// 3若该用户不存在，则修改失败
		return false;
	}

	@Override
	public List<UserModel> getAll() {
		return FileIoUtil.readFormFile(FILE_NAME);
	}

	@Override
	public List<UserModel> getbyCondition(UserQueryModel uqm) {
		List<UserModel> list = UserEbiFactory.getUserEbi().getAll();
		List<UserModel> results = new ArrayList<UserModel>();
		
		for(UserModel user : list){
			//反逻辑，卫条件: 外层判断用户输入是否是查询条件;内层判断该对象是否符合查询条件
			if(!StringComparison.stringEquals(user.getUuid(), uqm.getUuid())){
				continue;
			}//精确匹配
			
			//姓名就是模糊匹配
			if(!StringComparison.stringIndexOf(user.getName(), uqm.getName())){
				continue;
			}
			
			if(uqm.getType()>0){//外层判断
					if(user.getType()!=uqm.getType()){//内层判断--精确查询
						continue;
				}
			}
			
			results.add(user);
		}
		
		return results;
	}

	@Override
	public UserModel getSingle(String uuid) {
		// 1先把已有的数据反序列化(读)出来
		List<UserModel> list = FileIoUtil.readFormFile(FILE_NAME);

		// 2判断该用户是否已经存在,存在就返回那个用户
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUuid().equals(uuid)) {
				return list.get(i);
			}
		}
		
		// 3若该用户不存在,返回null
		return null;
	}

}
