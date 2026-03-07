package com.wangagain.wx.controller.admin;

import com.wangagain.wx.service.admin.UsersService;
import com.wangagain.wx.utils.ResultLogin;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller + @ResponseBody
@CrossOrigin // 解决跨域问题
@RequestMapping("/admin")
public class UsersController {

    // 创建业务层对象
    @Resource
    private UsersService usersService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ResultLogin login(String name, String password) throws Exception {
        System.out.println("用户名："+name+" 密码："+password);
        // 调用业务层
        return usersService.login(name, password);


    }
}
