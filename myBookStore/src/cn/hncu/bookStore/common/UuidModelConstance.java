package cn.hncu.bookStore.common;
/**
 * 
 * @author ³ÂºÆÏè
 *
 * @version 1.0
 */
public enum UuidModelConstance {
	USER("UserMedel"),
	BOOK("BookMedel"),
	IN_MAIN("InMainMedel"),
	OUT_MAIN("OutMainModel"),
	IN_DETAIL("InDetailModel"),
	OUT_DETAIL("OutDetailModel");
	
	private final String name;
	private UuidModelConstance(String name){
		this.name=name;
	}
	
	public String getName(){
		return name;
	}
	
}
