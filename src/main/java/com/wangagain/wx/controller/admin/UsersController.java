package com.wangagain.wx.controller.admin;

import com.wangagain.wx.service.admin.AccountService;
import com.wangagain.wx.utils.ResultLogin;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller + @ResponseBody
@CrossOrigin // 解决跨域问题
@RequestMapping("/admin")
public class UsersController {

    // 创建业务层对象
    @Resource
    private AccountService accountService;

    @PostMapping("/login")
    public ResultLogin login(@RequestBody LoginRequest loginRequest){
        System.out.println("用户名："+loginRequest.getName()+" 密码："+loginRequest.getPassword());
        // 调用业务层
        try {
            return accountService.login(loginRequest.getName(), loginRequest.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    // 登录请求对象
    public static class LoginRequest {
        private String name;
        private String password;
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public String getPassword() {
            return password;
        }
        
        public void setPassword(String password) {
            this.password = password;
        }
    }
}