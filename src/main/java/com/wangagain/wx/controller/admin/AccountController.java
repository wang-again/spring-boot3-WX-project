package com.wangagain.wx.controller.admin;

import com.wangagain.wx.service.admin.AccountService;
import com.wangagain.wx.utils.CaptchaUtil;
import com.wangagain.wx.utils.ResultLogin;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller + @ResponseBody
@CrossOrigin // 解决跨域问题
@RequestMapping("/admin")
public class AccountController {

    // 创建业务层对象
    @Resource
    private AccountService accountService;

    @PostMapping("/login")
    public ResultLogin login(@RequestBody LoginRequest loginRequest, HttpSession session){
        System.out.println("用户名："+loginRequest.getName()+" 密码："+loginRequest.getPassword()+" 验证码："+loginRequest.getCode());

        // 验证验证码
        String sessionCode = (String) session.getAttribute("captcha");
        if (sessionCode == null || !sessionCode.equalsIgnoreCase(loginRequest.getCode())) {
            return new ResultLogin(1004, "验证码错误", null);
        }

        // 调用业务层
        try {
            return accountService.login(loginRequest.getName(), loginRequest.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 生成验证码
    @GetMapping("/captcha")
    public ResultLogin captcha(HttpSession session) {
        CaptchaUtil.CaptchaInfo captchaInfo = CaptchaUtil.generateCaptcha();
        // 将验证码存储到session中
        session.setAttribute("captcha", captchaInfo.getCode());
        return new ResultLogin(1002, "验证码生成成功", captchaInfo.getImage());
    }

    // 登录请求对象
    public static class LoginRequest {
        private String name;
        private String password;
        private String code;

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

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}