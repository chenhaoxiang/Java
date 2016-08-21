package cn.hncu;

import sun.misc.BASE64Encoder;

public class EncodeDemo {
	
	public static void main(String[] args) {
		BASE64Encoder be = new BASE64Encoder();
		
		String name = "chenhaoxiang0117";
		String pwd = "chenhaoxiang52**";
		name = be.encode(name.getBytes());
		pwd = be.encode(pwd.getBytes());
		//英文可以不指定编码
		System.out.println(name);
		System.out.println(pwd);
	}
	
}
