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
            ResultLogin result = accountService.login(loginRequest.getName(), loginRequest.getPassword());
            if (result.getCode() == 1002) { // 登录成功
                // 存储用户信息到session
                session.setAttribute("user", result.getData());
                session.setAttribute("isLoggedIn", true);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultLogin(1003, "系统错误，请联系管理员", null);
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

    // 修改密码 - POST方法
    @PostMapping("/updatePassword")
    public ResultLogin updatePasswordPost(@RequestBody UpdatePasswordRequest updatePasswordRequest, HttpSession session) {
        try {
            System.out.println("修改密码请求：用户名=" + updatePasswordRequest.getName() + ", 旧密码长度=" + (updatePasswordRequest.getOldPassword() != null ? updatePasswordRequest.getOldPassword().length() : 0) + ", 新密码长度=" + (updatePasswordRequest.getNewPassword() != null ? updatePasswordRequest.getNewPassword().length() : 0));
            ResultLogin result = accountService.updatePassword(updatePasswordRequest.getName(), updatePasswordRequest.getOldPassword(), updatePasswordRequest.getNewPassword());
            System.out.println("修改密码结果：" + result.getCode() + "-" + result.getMsg());
            
            if (result.getCode() == 1000) { // 密码修改成功
                // 清除登录状态
                session.invalidate();
                System.out.println("密码修改成功，已清除登录状态");
            }
            
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultLogin(1003, "系统错误，请联系管理员", null);
        }
    }

    // 修改密码 - GET方法
    @GetMapping("/updatePassword")
    public ResultLogin updatePasswordGet(String name, String oldPassword, String newPassword, HttpSession session) {
        try {
            System.out.println("修改密码，用户名：" + name);
            ResultLogin result = accountService.updatePassword(name, oldPassword, newPassword);
            
            if (result.getCode() == 1000) { // 密码修改成功
                // 清除登录状态
                session.invalidate();
                System.out.println("密码修改成功，已清除登录状态");
            }
            
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultLogin(1003, "系统错误，请联系管理员", null);
        }
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

    // 修改密码请求对象
    public static class UpdatePasswordRequest {
        private String name;
        private String oldPassword;
        private String newPassword;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOldPassword() {
            return oldPassword;
        }

        public void setOldPassword(String oldPassword) {
            this.oldPassword = oldPassword;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }
    }
}