package cn.hncu.xmlImpl;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MyAutoProxy implements BeanPostProcessor,ApplicationContextAware{
	private ApplicationContext applicationContext=null;
	
	//bean创建之前调用
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;//在这里，我们直接放行
	}
	
	//bean创建之后调用
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		ProxyFactoryBean factory = new ProxyFactoryBean();
		//把原型对象放入代理工厂
		factory.setTarget(bean);
		//在这里
		Advisor adv = applicationContext.getBean(Advisor.class);
		factory.addAdvisor(adv);
		//返回被代理后的对象
		return factory.getObject();
	}
	
	//拿到原来的spring中的容器
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext=applicationContext;
	}
	
}
