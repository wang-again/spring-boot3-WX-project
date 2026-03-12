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
    public ResultLogin login(String name, String password) {
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
            return new ResultLogin(1003, "系统错误，请联系管理员", null);
        }
    }

    @Override
    public ResultLogin register(String name, String password) {
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
            return new ResultLogin(1003, "系统错误，请联系管理员", null);
        }
    }

    @Override
    public ResultLogin updatePassword(String name, String password) {
        try{
            String encodePwd = passwordEncoder.encode(password);
            Account user = accountMapper.findUserExist(name);
            if (passwordEncoder.matches(password, user.getuPwd()))
            return new ResultLogin(1004,"修改密码不能原密码相同",null);
            int i = accountMapper.updatePassword(user.getuId(), encodePwd);
            user.setuPwd(encodePwd);
            return (i>0)?new ResultLogin(1000,"密码修改成功",user):new ResultLogin(1002,"其他元婴",null);
        }catch (Exception e){
            return new ResultLogin(1003,"数据库故障",null);
        }
    }

}