package com.wangagain.wx.utils;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wangagain.wx.entity.admin.Account;
import com.wangagain.wx.mapper.admin.AccountMapper;

@Component
public class PasswordUpdater {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void updatePasswords() {
        // 获取所有用户
        java.util.List<Account> users = accountMapper.allUsers();

        for (Account user : users) {
            String plainPassword = user.getuPwd();
            // 检查密码是否已经是加密格式（bcrypt密码长度通常大于60）
            if (plainPassword.length() < 60) {
                // 加密密码
                String encodedPassword = passwordEncoder.encode(plainPassword);
                // 更新密码
                accountMapper.updatePassword(user.getuId(), encodedPassword);
                System.out.println("Updated password for user: " + user.getuName());
            }
        }
    }
}