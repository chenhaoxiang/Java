package cn.hncu.reg.service;
import cn.hncu.domain.User;
import cn.hncu.reg.dao.RegDAO;
import cn.hncu.reg.dao.RegDaoJdbc;
public class RegServiceImpl implements IRegService{
	//依赖注入
	private RegDAO dao = new RegDaoJdbc();
	@Override
	public User reg(User user) {
		return dao.reg(user);
	}
	@Override
	public int active(String acode) {
		return dao.active(acode);
	}
}
