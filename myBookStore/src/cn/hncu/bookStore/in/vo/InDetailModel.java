package cn.hncu.bookStore.in.vo;

import java.io.Serializable;

/**
 * 进货明细管理的值对象封装
 * @author 陈浩翔
 *
 * @version 1.0
 */
public class InDetailModel implements Serializable{
	//进货明细编号
	private String uuid;
	//进货单编号
	private String inId;
	//图书编号
	private String bookId;
	//进货数量
	private int sumNum;
	//进货总金额
	private double sumMoney;
	
	//为把书名显示给用户看，给bookUuid添加一个bookName属性
	private String bookName;
	
	
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getInId() {
		return inId;
	}
	public void setInId(String inId) {
		this.inId = inId;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public int getSumNum() {
		return sumNum;
	}
	public void setSumNum(int sumNum) {
		this.sumNum = sumNum;
	}
	public double getSumMoney() {
		return sumMoney;
	}
	public void setSumMoney(double sumMoney) {
		this.sumMoney = sumMoney;
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
		InDetailModel other = (InDetailModel) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return uuid +", "+bookName + ", " + sumNum
				+ "本, " + sumMoney + "元";
	}
	
	
	
	
	
}
