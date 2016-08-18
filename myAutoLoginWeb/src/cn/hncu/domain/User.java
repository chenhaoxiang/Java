package cn.hncu.domain;

/**
 * @author 陈浩翔
 *
 * 2016-8-18
 */
public class User {
	private String name;
	private String pwd;
	public User() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
