package cn.hncu.bookStore.out.vo;

/**
 * 销售查询值对象封装
 * @author 陈浩翔
 *
 * @version 1.0
 */
public class OutMainQueryModel extends OutMainModel {
	
	//需要查询的最大时间
	private long outDate2;
	
	/**
	 * 
	 * @return---需要查询的最大时间值
	 */
	public long getOutDate2() {
		return outDate2;
	}
	
	/**
	 * 
	 * @param outDate2---设置需要查询的最大时间
	 */
	public void setOutDate2(long inDate2) {
		this.outDate2 = outDate2;
	}

}
