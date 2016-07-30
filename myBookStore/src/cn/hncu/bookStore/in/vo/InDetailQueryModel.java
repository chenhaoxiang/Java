package cn.hncu.bookStore.in.vo;

/**
 * 进货明细查询值对象封装
 * @author 陈浩翔
 *
 * @version 1.0
 */
public class InDetailQueryModel extends InDetailModel {
	
	//需要查询的最大进货数量
	private int sumNum2;
	//需要查询的最大进货金额
	private double sumMoney2;
	
	/**
	 * 
	 * @return---返回需要查询的最大进货数量
	 */
	public int getSumNum2() {
		return sumNum2;
	}
	/**
	 * 
	 * @param sumNum2---设置需要查询的最大进货数量
	 */
	public void setSumNum2(int sumNum2) {
		this.sumNum2 = sumNum2;
	}
	
	/**
	 * 
	 * @return---返回需要查询的最大进货金额
	 */
	public double getSumMoney2() {
		return sumMoney2;
	}
	
	/**
	 * 
	 * @param sumMoney2---设置需要查询的最大进货金额
	 */
	public void setSumMoney2(double sumMoney2) {
		this.sumMoney2 = sumMoney2;
	}

}
