package cn.hncu.javaImpl;

import org.springframework.aop.ThrowsAdvice;

public class ThrowException implements ThrowsAdvice{
	public  void  afterThrowing(Exception e)  throws  Throwable{  
        System.out.println("出异常了..."+e);
    }
}
