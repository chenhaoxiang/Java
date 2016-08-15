package cn.hncu.domain;

import java.util.ArrayList;
import java.util.List;

public class Stud {
	
	private String id;
	private String name;
	
	//※一对多中的一方: 添加一个集合(用set也行)以表示该关系
	private List<Book > books = new ArrayList<Book>();

	public Stud() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
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
		Stud other = (Stud) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	//注意,如果要添加toString()方法，注意一对多关系中的信息输出嵌套
	
	
}
