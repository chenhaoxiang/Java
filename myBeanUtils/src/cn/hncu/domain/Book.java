package cn.hncu.domain;

/**
 * @author 陈浩翔
 *
 * 2016-8-25
 */
public class Book {
	private String uuid;
	private String name;
	private double inPrice;
	private double outPrice;
	private int num;
	
	public Book() {
		super();
	}
	public Book(String uuid, String name, double inPrice, double outPrice,
			int num) {
		super();
		this.uuid = uuid;
		this.name = name;
		this.inPrice = inPrice;
		this.outPrice = outPrice;
		this.num = num;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getInPrice() {
		return inPrice;
	}
	public void setInPrice(double inPrice) {
		this.inPrice = inPrice;
	}
	public double getOutPrice() {
		return outPrice;
	}
	public void setOutPrice(double outPrice) {
		this.outPrice = outPrice;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
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
		Book other = (Book) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Book [uuid=" + uuid + ", name=" + name + ", inPrice=" + inPrice
				+ ", outPrice=" + outPrice + ", num=" + num + "]";
	}
}
