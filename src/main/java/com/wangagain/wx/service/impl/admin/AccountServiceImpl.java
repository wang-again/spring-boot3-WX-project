package com.wangagain.wx.service.impl.admin;

import com.wangagain.wx.entity.admin.Account;
import com.wangagain.wx.mapper.admin.AccountMapper;
import com.wangagain.wx.service.admin.AccountService;
import com.wangagain.wx.utils.ResultLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResultLogin login(String name, String password) throws Exception {
        try {
            Account user = accountMapper.findUserExist(name);
            if (user == null) {
                // 用户不存在
                return new ResultLogin(1000, "用户名不存在", null);
            }
            // 使用密码编码器验证密码
            if (!passwordEncoder.matches(password, user.getuPwd())) {
                // 密码错误
                return new ResultLogin(1001, "密码错误", null);
            }
            return new ResultLogin(1002, "登录成功", user);
        } catch (Exception e) {
            // 数据库表不存在或其他错误
            System.out.println("登录错误: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public ResultLogin register(String name, String password) throws Exception {
        try {
            String encodePwd = passwordEncoder.encode(password);
            int register = accountMapper.register(name, encodePwd);
            if (register == 0) {
                return new ResultLogin(1000, "用户名存在", null);
            }
            return new ResultLogin(1001, "注册成功", null);
        } catch (Exception e) {
            // 数据库表不存在或其他错误
            System.out.println("登录错误: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public ResultLogin updatePassword(String name, String oldPassword, String newPassword) throws Exception {
        try{
            System.out.println("=== 修改密码开始 ===");
            System.out.println("用户名: " + name);
            
            Account user = accountMapper.findUserExist(name);
            if (user == null) {
                System.out.println("用户不存在: " + name);
                return new ResultLogin(1000, "用户名不存在", null);
            }
            
            System.out.println("用户存在，ID: " + user.getuId());
            System.out.println("当前密码(加密后): " + user.getuPwd());
            
            // 验证旧密码
            boolean oldPasswordMatch = passwordEncoder.matches(oldPassword, user.getuPwd());
            System.out.println("旧密码验证结果: " + oldPasswordMatch);
            
            if (!oldPasswordMatch) {
                return new ResultLogin(1001, "旧密码错误", null);
            }
            
            // 检查新密码是否与旧密码相同
            boolean newPasswordSame = passwordEncoder.matches(newPassword, user.getuPwd());
            System.out.println("新密码与旧密码是否相同: " + newPasswordSame);
            
            if (newPasswordSame) {
                return new ResultLogin(1004, "新密码不能与原密码相同", null);
            }
            
            String encodePwd = passwordEncoder.encode(newPassword);
            System.out.println("新密码(加密后): " + encodePwd);
            
            int i = accountMapper.updatePassword(user.getuId(), encodePwd);
            System.out.println("更新结果: " + i);
            
            user.setuPwd(encodePwd);
            System.out.println("=== 修改密码结束 ===");
            
            return (i>0)?new ResultLogin(1000,"密码修改成功",user):new ResultLogin(1002,"其他原因",null);
        }catch (Exception e){
            System.out.println("修改密码异常: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

}