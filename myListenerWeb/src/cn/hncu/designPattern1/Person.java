package cn.hncu.designPattern1;

public class Person {
	private String name;
	private IPersonRunListener listener1;
	private IPersonRunListener listener2;
	public Person(String name) {
		super();
		this.name = name;
	}
	
	public void run(){
		if(listener1!=null){
			listener1.fighting();
		}
		System.out.println(name+"正在跑...");
		if(listener2!=null){
			listener2.fighting();
		}
	}
	
	public void addBefore(IPersonRunListener listener){
		this.listener1=listener;
	}
	
	public void addAfter(IPersonRunListener listener){
		this.listener2=listener;
	}
	
}

interface IPersonRunListener{
	public void fighting();
}
