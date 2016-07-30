package cn.hncu.bookStore.book.vo;

import java.io.Serializable;

/**
 * 值对象封装
 * 
 * @author 陈浩翔
 * @version 1.0
 */
public class BookModel implements Serializable{//必须实现这个接口
	private String uuid;//图书ID
	private String name;//图书名字
	private double inPrice;//图书的进价
	private double salePrice;//图书的售价
	
	/**
	 * 
	 * @return 图书的ID
	 */
	public String getUuid() {
		return uuid;
	}
	/**
	 * 
	 * @param uuid ---设置图书的ID
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	/**
	 * 
	 * @return 图书的名字
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @param name--设置图书的名字
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return 得到图书的进价
	 */
	public double getInPrice() {
		return inPrice;
	}
	
	/**
	 * 
	 * @param inPrice---设置图书的进价
	 */
	public void setInPrice(double inPrice) {
		this.inPrice = inPrice;
	}
	
	/**
	 * 
	 * @return ---返回图书的售价
	 */
	public double getSalePrice() {
		return salePrice;
	}
	
	/**
	 * 
	 * @param outPrice---设置图书的售价
	 */
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
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
		BookModel other = (BookModel) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return uuid + ", " + name + ", 进价="
				+ inPrice + ", 售价" + salePrice;
	}
	
}
