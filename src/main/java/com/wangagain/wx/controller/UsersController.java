package com.wangagain.wx.controller;

import com.wangagain.wx.service.UsersService;
import com.wangagain.wx.utils.ResultLogin;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller + @ResponseBody
public class UsersController {

    // 创建业务层对象
    @Resource
    private UsersService usersService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ResultLogin login(String name, String password){
        // 调用业务层
        return usersService.login(name, password);


    }
}
