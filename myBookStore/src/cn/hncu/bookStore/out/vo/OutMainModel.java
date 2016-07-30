package cn.hncu.bookStore.out.vo;

import java.io.Serializable;

import cn.hncu.bookStore.util.DateUtil;

/**
 * 销售管理值对象封装
 * @author 陈浩翔
 *
 * @version 1.0
 */
public class OutMainModel implements Serializable, Comparable<OutMainModel>{
	//销售单编号
	private String uuid;
	//销售时间
	private long outDate;
	//销售人员编号
	private String outUserId;
	
	/* 
	   如果某个字段是外键，同时又需要在当前表中相应表现层显示出用户能看得懂
	 的信息,则需要补一个专用于信息显示(给用户看)的字段。
	 */
	private String outUserName;
	
	public String getOutUserName() {
		return outUserName;
	}
	
	public void setOutUserName(String outUserName) {
		this.outUserName = outUserName;
	}

	/**
	 * 
	 * @return ---返回销售单编号(String型)
	 */
	public String getUuid() {
		return uuid;
	}
	
	/**
	 * 
	 * @param uuid---设置销售单编号(String型)
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	/**
	 * 
	 * @return---返回销售时间(long型)
	 */
	public long getOutDate() {
		return outDate;
	}
	
	/**
	 * 
	 * @param outDate---设置销售时间(long型)
	 */
	public void setOutDate(long outDate) {
		this.outDate = outDate;
	}
	
	/**
	 * 
	 * @return---返回销售人员编号(String型)
	 */
	public String getOutUserId() {
		return outUserId;
	}
	
	/**
	 * 
	 * @param outUserId---设置销售人员编号(String型)
	 */
	public void setOutUserId(String outUserId) {
		this.outUserId = outUserId;
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
		OutMainModel other = (OutMainModel) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return uuid + ", " + outUserName
				+ ", " + DateUtil.long2String(outDate);
	}

	@Override
	public int compareTo(OutMainModel o) {
		return Integer.parseInt(uuid)-Integer.parseInt(o.uuid);
	}
}
