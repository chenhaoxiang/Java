package cn.hncu.bookStore.common.uuid.vo;

import java.io.Serializable;

/**
 * Uuid的值对象封装
 * 一个模块名称和一个uuid
 * @author 陈浩翔
 *
 * @version 1.0
 */
public class UuidModel implements Serializable{//用对象流读取，必须实现的接口
	private String modelName;
	private int currentNum;
	
	/**
	 * 
	 * @return 返回模块的名称
	 */
	public String getModelName() {
		return modelName;
	}
	
	/**
	 * 
	 * @param modelName---要设置的模块的名称
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	/**
	 * 
	 * @return ---返回当前的值
	 */
	public int getCurrentNum() {
		return currentNum;
	}
	
	/**
	 * 
	 * @param currentNum--设置当前的值
	 */
	public void setCurrentNum(int currentNum) {
		this.currentNum = currentNum;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((modelName == null) ? 0 : modelName.hashCode());
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
		UuidModel other = (UuidModel) obj;
		if (modelName == null) {
			if (other.modelName != null)
				return false;
		} else if (!modelName.equals(other.modelName))
			return false;
		return true;
	}
	
	
}
