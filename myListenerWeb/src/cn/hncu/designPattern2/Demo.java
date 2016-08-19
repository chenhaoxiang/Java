package cn.hncu.designPattern2;

public class Demo {
	
	public static void main(String[] args) {
		Person p1 = new Person("张三");
		Person p2 = new Person("Jack");
		IPersonRunListener listener = new IPersonRunListener() {
			@Override
			public void fighting(PersonEvent pe) {
				System.out.println(pe.getSource()+"已经跑完了...");
				if(pe.getName().equals("张三")){
					System.out.println(pe.getName()+"跑到了第一名...");
				}
			}
		};
		p1.addPersonListener(listener);
		p2.addPersonListener(listener);
		p1.run();
		p2.run();
		
		
		Person p3 = new Person("李四");
		p3.addPersonListener(new DefaultCatListener());
		p3.run();
	}
}

