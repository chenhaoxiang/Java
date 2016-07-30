package cn.hncu.bookStore.user.business.ebo;

import java.util.List;

import cn.hncu.bookStore.common.UserTypeEnum;
import cn.hncu.bookStore.common.UuidModelConstance;
import cn.hncu.bookStore.common.uuid.dao.factory.UuidDaoFactory;
import cn.hncu.bookStore.common.uuid.vo.UuidModel;
import cn.hncu.bookStore.user.business.ebi.UserEbi;
import cn.hncu.bookStore.user.dao.dao.UserDao;
import cn.hncu.bookStore.user.dao.factory.UserDaoFactory;
import cn.hncu.bookStore.user.vo.UserModel;
import cn.hncu.bookStore.user.vo.UserQueryModel;
/**
 * 逻辑层的实现类
 * @author 陈浩翔
 * @version 1.0
 */
public class UserEbo implements UserEbi{
	//注入
	private UserDao dao = UserDaoFactory.getUserDao();
	
	@Override
	public boolean create(UserModel user) {
		//如果表现层中对user对象的数据没有封装完整，那么在这里要为它补全
		//调用uuid模块的dao层来自动获取当前user对象的uuid
		String uuid = UuidDaoFactory.getUuidDao().getNextUuid(UuidModelConstance.USER);
		user.setUuid(uuid);
		
		return dao.create(user);
	}

	@Override
	public boolean delete(String uuid) {
		return dao.delete(uuid);
	}

	@Override
	public boolean update(UserModel user) {
		return dao.update(user);
	}

	@Override
	public List<UserModel> getAll() {
		return dao.getAll();
	}

	@Override
	public List<UserModel> getbyCondition(UserQueryModel uqm) {
		return dao.getbyCondition(uqm);
	}

	@Override
	public UserModel getSingle(String uuid) {
		return dao.getSingle(uuid);
	}

	@Override
	public List<UserModel> getAllIn() {
		UserQueryModel uqm = new UserQueryModel();
		uqm.setType(UserTypeEnum.IN.getType());
		return getbyCondition(uqm);
	}

	@Override
	public UserModel getUserByName(String name) {
		List<UserModel> list = getAll();
		for(UserModel model: list){
			if(model.getName().equals(name)){
				return model;
			}
		}		
		return null;
	}

	@Override
	public List<UserModel> getAllOut() {
		UserQueryModel uqm = new UserQueryModel();
		uqm.setType(UserTypeEnum.OUT.getType());
		return getbyCondition(uqm);
	}
}
