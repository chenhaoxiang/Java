package cn.hncu.demo1.login;

import cn.hncu.demo1.domain.Person;
import cn.hncu.demo1.login.service.ILoginService;

public class LoginAction {
	private ILoginService service = null;
	private Person person = null;
	//如果要注入，需要注入的成员变量，必须写好set-get方法！
	public ILoginService getService() {
		return service;
	}
	public void setService(ILoginService service) {
		this.service = service;
	}
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public void execute(){
		service.login(person);
	}
	
}
