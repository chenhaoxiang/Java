package cn.hncu.javaImpl;

public class Person {
	public void run(){
		System.out.println("我在run...");
	}
	
	public void run(int i){
		System.out.println("我在run"+i+"...");
		throw  new  IllegalArgumentException();  
	}
	
	public void say(){
		System.out.println("我在say...");
	}
	
}
