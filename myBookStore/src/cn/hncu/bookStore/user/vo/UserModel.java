package cn.hncu.bookStore.user.vo;

import java.io.Serializable;

import cn.hncu.bookStore.common.UserTypeEnum;

/**
 * @author 陈浩翔
 * @version 1.0
 * 
 * <br/>
 * 用于保存用户信息的值对象<br/>
 * 1、可序列化<br/>
 * 2、私有化所有变量成员，补setter-getters方法<br/>
 * 3、写equals和hashCode方法----用主键(uuid)唯一标识码<br/>
 * 4、toString方法<br/>
 * 5,空参构造方法<br/>
 */

public class UserModel implements Serializable{
	private String uuid;//用户唯一标识码
	private String name;//用户名
	private int type;//用户类型
	private String pwd;//用户密码
	public UserModel() {
	}
	
	/**
	 * 功能：得到uuid-用户唯一的标识码
	 * 
	 * @return 返回uuid-用户唯一的标识码
	 */
	public String getUuid() {
		return uuid;
	}
	
	/**
	 * 功能：设置uuid-用户唯一的标识码
	 * @param uuid-用户唯一的标识码-String型参数
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	/**
	 * 功能：得到用户的用户名
	 * @return---name-用户名
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 功能：设置用户的用户名
	 * 
	 * @param name--用户设置的用户名，String型参数
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 功能：得到用户的类型：
	 *  1——表示为admin，可以进行全部操作
     *	2——表示为能操作图书模块的人员
	 *	3——表示为能操作进货模块的人员
	 *	4——表示为能操作销售模块的人员
	 *	5——表示为能操作库存模块的人员
	 * @return 用户的类型
	 */
	public int getType() {
		return type;
	}
	
	/**
	 *  功能：设置用户的类型：
	 *  1——表示为admin，可以进行全部操作
     *	2——表示为能操作图书模块的人员
	 *	3——表示为能操作进货模块的人员
	 *	4——表示为能操作销售模块的人员
	 *	5——表示为能操作库存模块的人员
	 * @param type--用户的类型-int型参数
	 */
	public void setType(int type) {
		this.type = type;
	}
	
	/**
	 *功能：得到用户的密码
	 * @return String型，用户的密码
	 */
	public String getPwd() {
		return pwd;
	}
	
	/**
	 * 功能：设置用户的密码
	 * @param pwd--String型参数
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserModel other = (UserModel) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return uuid + "," + name + "," + UserTypeEnum.getNameByType(type);
	}
	
}
