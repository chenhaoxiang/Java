package com.uifuture.user.controller;

import com.uifuture.user.entity.User;
import com.uifuture.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2018/2/6.
 * Time: 下午 8:46.
 * Explain:
 */
@RestController
public class UserContriller {

    @Autowired
    private UserService userService;
    @RequestMapping(value = {"/index","/"})
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    @RequestMapping("/insert")
    public @ResponseBody String insert(User user){
        Integer result = userService.insertUser(user);
        if(result.equals(1)){
            return "success";
        }
        return "error";
    }

    @RequestMapping("/find")
    public @ResponseBody User find(Integer id){
        return userService.findUserById(id);
    }


}
