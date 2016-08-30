package cn.hncu.domain;

public class Student {
	private String sId;
	private String sName;
	private Integer sAge;
	
	private String deptId;
	
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public Student() {
		super();
	}
	public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public Integer getsAge() {
		return sAge;
	}
	public void setsAge(Integer sAge) {
		this.sAge = sAge;
	}
	@Override
	public String toString() {
		return "Student [sId=" + sId + ", sName=" + sName + ", sAge=" + sAge
				+ ", deptId=" + deptId + "]";
	}
}
