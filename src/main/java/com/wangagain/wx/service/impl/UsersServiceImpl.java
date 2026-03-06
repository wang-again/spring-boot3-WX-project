package com.wangagain.wx.service.impl;

import com.wangagain.wx.entity.Users;
import com.wangagain.wx.mapper.UsersMapper;
import com.wangagain.wx.service.UsersService;
import com.wangagain.wx.utils.ResultLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public ResultLogin login(String name, String password) {

        Users user = usersMapper.login(name);
        if(user == null){
            // 用户不存在
            return new ResultLogin(1000,"用户名不存在", null);
        }else{
            // 用户存在
            if(user.getuPwd().equals(password)){
                // 登录成功
                return new ResultLogin(1001,"登录成功", user);
            }else{
                // 密码错误
                return new ResultLogin(1002,"密码错误", null);
            }
        }
    }
}
