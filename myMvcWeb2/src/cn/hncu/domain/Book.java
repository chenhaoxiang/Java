package cn.hncu.domain;

/*
alter table book add constraint fk_book foreign key (studid) references stud(id);
设置studid为book的外键(fk_book)为stud的主键id
 */

public class Book {
	
	private Integer id;
	private String namem;
	private Double price;
	
	// ※一对多关系中的多方(外键字段): 在写JavaBean时，要包含对方(一方)的整个值对象类型的变量
	private Stud stud;//设置书的主人

	public Book() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNamem() {
		return namem;
	}

	public void setNamem(String namem) {
		this.namem = namem;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Stud getStud() {
		return stud;
	}

	public void setStud(Stud stud) {
		this.stud = stud;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
