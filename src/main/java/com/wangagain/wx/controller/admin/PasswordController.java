package com.wangagain.wx.controller.admin;

import com.wangagain.wx.utils.PasswordUpdater;
import com.wangagain.wx.utils.ResultLogin;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class PasswordController {

    @Resource
    private PasswordUpdater passwordUpdater;

    @GetMapping("/update-passwords")
    public ResultLogin updatePasswords() {
        try {
            passwordUpdater.updatePasswords();
            return new ResultLogin(1002, "密码更新成功", null);
        } catch (Exception e) {
            return new ResultLogin(1003, "密码更新失败", null);
        }
    }
}