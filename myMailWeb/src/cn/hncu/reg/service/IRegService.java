package cn.hncu.reg.service;

import cn.hncu.domain.User;

public interface IRegService {
    public User reg(User user);
    public int active(String acode);
}
