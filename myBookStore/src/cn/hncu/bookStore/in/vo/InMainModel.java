package cn.hncu.bookStore.in.vo;

import java.io.Serializable;

import cn.hncu.bookStore.util.DateUtil;

/**
 * 进货管理值对象封装
 * @author 陈浩翔
 *
 * @version 1.0
 */
public class InMainModel implements Serializable, Comparable<InMainModel>{
	//进货单编号
	private String uuid;
	//进货时间
	private long inDate;
	//进货人员编号
	private String inUserId;
	
	/* 
	   如果某个字段是外键，同时又需要在当前表中相应表现层显示出用户能看得懂
	 的信息,则需要补一个专用于信息显示(给用户看)的字段。
	 */
	private String inUserName;
	
	public String getInUserName() {
		return inUserName;
	}
	
	public void setInUserName(String inUserName) {
		this.inUserName = inUserName;
	}

	/**
	 * 
	 * @return ---返回进货单编号(String型)
	 */
	public String getUuid() {
		return uuid;
	}
	
	/**
	 * 
	 * @param uuid---设置进货单编号(String型)
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	/**
	 * 
	 * @return---返回进货时间(long型)
	 */
	public long getInDate() {
		return inDate;
	}
	
	/**
	 * 
	 * @param inDate---设置进货时间(long型)
	 */
	public void setInDate(long inDate) {
		this.inDate = inDate;
	}
	
	/**
	 * 
	 * @return---返回进货人员编号(String型)
	 */
	public String getInUserId() {
		return inUserId;
	}
	
	/**
	 * 
	 * @param inUserId---设置进货人员编号(String型)
	 */
	public void setInUserId(String inUserId) {
		this.inUserId = inUserId;
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
		InMainModel other = (InMainModel) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return uuid + ", " + inUserName
				+ ", " + DateUtil.long2String(inDate);
	}

	@Override
	public int compareTo(InMainModel o) {
		return Integer.parseInt(uuid)-Integer.parseInt(o.uuid);
	}
	
	
	
}
