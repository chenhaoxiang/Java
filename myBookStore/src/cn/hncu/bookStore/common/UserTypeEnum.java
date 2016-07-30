package cn.hncu.bookStore.common;

/**
 * 功能:用户类型的枚举！<br/>
 * 定义在公共模块。<br/>
 * 变量：<br/>
 *  ADMIN(1,"超级管理员"),<br/>
 *	BOOK(2,"图书管理员"),<br/>
 *	IN(3,"进货管理员"),<br/>
 *	OUT(4,"销售管理员"),<br/>
 *  STOCK(5,"库存管理员");<br/>
 * @author chx
 * @version 1.0
 */
public enum UserTypeEnum {
	ADMIN(1,"超级管理员"),
	BOOK(2,"图书管理员"),
	IN(3,"进货管理员"),
	OUT(4,"销售管理员"),
	STOCK(5,"库存管理员");
	
	private final int type;
	private final String name;
	
	/**
	 * 初始化枚举变量名字
	 * @param type---枚举变量对应的整型数字
	 * @param name---枚举变量对应的String型名字
	 */
	private UserTypeEnum(int type, String name) {
		this.type=type;
		this.name=name;
	}
	
	/**
	 * 得到当前枚举变量的数字
	 * @return---type-编号
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * 得到当前枚举变量的中文名字
	 * @return---name-中文名字
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 根据枚举变量的int数字得到数字对应的枚举变量的中文名字
	 * @param type---需要传入的int型参数
	 * @return ---如果存在这样的数字对应的枚举变量，就返回这个枚举变量的中文名字。
	 *    <br/>---如果不存在这样的数字对应的枚举变量，就抛出一个异常信息。
	 */
	public static String getNameByType(int type){
		for(UserTypeEnum userType:UserTypeEnum.values()){
			if(userType.getType()==type){
				return userType.getName();
			}
		}
		throw new IllegalArgumentException("枚举中没有对应的用户类型:"+type);
	}
	
	/**
	 * 根据枚举变量的name中文名字得到name对应的枚举变量的int型type
	 * @param name---需要传入的String型名字
	 * @return ---如果存在这样的名字对应的枚举变量，就返回这个枚举变量对应的type-int
	 *   <br/> ---如果不存在这样的名字对应的枚举变量，就抛出一个异常信息
	 */ 
	public static int getTypeByName(String name){
		for(UserTypeEnum userType:UserTypeEnum.values()){
			if(userType.getName().equals(name)){
				return userType.getType();
			}
		}
		throw new IllegalArgumentException("枚举中没有对应的用户类型:"+name);
	}
}
