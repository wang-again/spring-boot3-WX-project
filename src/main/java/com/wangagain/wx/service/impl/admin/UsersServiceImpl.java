package com.wangagain.wx.service.impl.admin;

import com.wangagain.wx.entity.Users;
import com.wangagain.wx.mapper.UsersMapper;
import com.wangagain.wx.service.admin.UsersService;
import com.wangagain.wx.utils.ResultLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
    private final String AES_TOKEN = "asdfa48210123456";
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResultLogin login(String name, String password) throws Exception {
        Users user = usersMapper.findUserExist(name);
        if (user == null) {
            return new ResultLogin(1000, "用户名不存在", null);
            // 用户不存在
        }
        if (passwordEncoder.matches(password, user.getUPwd())) return new ResultLogin(1001, "密码错误", null);
        // 密码错误
        return new ResultLogin(1002, "登录成功", user);
    }

@Override
public ResultLogin register(String name, String password) throws Exception {
    Users user = usersMapper.findUserExist(name);
    if (user != null) return new ResultLogin(1000, "用户名已存在", null);
    int rows = usersMapper.register(name, password);
    if (rows > 0) return new ResultLogin(1002, "注册成功", null);
    return new ResultLogin(1001, "注册失败", null);
}
}
