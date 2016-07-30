package cn.hncu.bookStore.in.vo;

/**
 * 进货查询值对象封装
 * @author 陈浩翔
 *
 * @version 1.0
 */
public class InMainQueryModel extends InMainModel {
	
	//需要查询的最大时间
	private long inDate2;
	
	/**
	 * 
	 * @return---需要查询的最大时间值
	 */
	public long getInDate2() {
		return inDate2;
	}
	
	/**
	 * 
	 * @param inDate2---设置需要查询的最大时间
	 */
	public void setInDate2(long inDate2) {
		this.inDate2 = inDate2;
	}

}
