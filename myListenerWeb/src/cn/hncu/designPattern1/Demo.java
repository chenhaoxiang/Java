package cn.hncu.designPattern1;

public class Demo {
	
	public static void main(String[] args) {
		Person person = new Person("张三");
		IPersonRunListener listener = new IPersonRunListener() {
			@Override
			public void fighting() {
				//这里可以做很多事，不是只能输出哦
				//不过由于还没写Event对象，所以拿不到是谁调用的
				System.out.println("先做好准备工作...");
			}
		};
		person.addBefore(listener);
		
		A a = new A();
		
		person.addAfter(a);
		
		person.run();
	}
}

class A implements IPersonRunListener{
	@Override
	public void fighting() {
		//这里可以做很多事，不是只能输出哦
		//不过由于还没写Event对象，所以拿不到是谁调用的
		System.out.println("跑完了，休息休息...");
	} 
}