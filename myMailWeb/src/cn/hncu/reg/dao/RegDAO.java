package cn.hncu.reg.dao;

import cn.hncu.domain.User;

public interface RegDAO {
	public User reg(User user);
	public int active(String acode);
}
