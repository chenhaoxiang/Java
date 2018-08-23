package cn.hncu.domain;

/**
 * @author 陈浩翔
 *
 * 2016-8-25
 */
public class Address {
	private String province;//省份
	private String city;//城市
	public Address() {
	}
	public Address(String province, String city) {
		this.province = province;
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "Address [province=" + province + ", city=" + city + "]";
	}
}
