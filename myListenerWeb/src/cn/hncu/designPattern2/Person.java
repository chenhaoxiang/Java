package cn.hncu.designPattern2;

public class Person {
	private String name;
	private IPersonRunListener listener;
	
	
	public Person(String name) {
		super();
		this.name = name;
	}

	public void run(){
		System.out.println(name+"开始跑了..");
		if(listener!=null){
			listener.fighting(new PersonEvent(this));
		}
	}
	
	public void addPersonListener(IPersonRunListener listener){
		this.listener=listener;
	}
	
	public String getName(){
		return name;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", listener=" + listener + "]";
	}
	
	
	
}

interface IPersonRunListener {
	public void fighting(PersonEvent pe);
}

class PersonEvent{
	Person p = null;
	public PersonEvent(Person p) {
		this.p = p;
	}
	
	public String getName(){
		return p.getName();
	}
	
	public Object getSource(){
		return p;
	}
}

//我们还可以写一个帮我们实现了接口的基本类
//里面写我们通用的模板，如果我们继承这个类，我们就可以不写了。
//有功能不一样的地方，我们就自己写，覆盖这个类的方法
class DefaultCatListener implements IPersonRunListener {

	@Override
	public void fighting(PersonEvent pe) {
		System.out.println("默认的动作...");
	}
}


