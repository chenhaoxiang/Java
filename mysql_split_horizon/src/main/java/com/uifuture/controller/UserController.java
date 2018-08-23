package com.uifuture.controller;

import com.uifuture.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2018/2/5.
 * Time: 下午 10:44.
 * Explain:
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 演示注册的入口
     * @param name
     * @param pwd
     * @return
     */
    @RequestMapping("/regit")
    public String regit(String name, String pwd) {
        return userService.regit(name, pwd);
    }

    /**
     * 演示获取name
     * @param id
     * @return
     */
    @RequestMapping("/get")
    public String get(Long id) {
        return userService.get(id);
    }

}
