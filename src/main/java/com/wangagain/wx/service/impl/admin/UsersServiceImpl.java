package com.wangagain.wx.service.impl.admin;

import com.wangagain.wx.entity.Users;
import com.wangagain.wx.mapper.UsersMapper;
import com.wangagain.wx.service.admin.UsersService;
import com.wangagain.wx.utils.AESUtil;
import com.wangagain.wx.utils.ResultLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
    private final String AES_TOKEN = "asdfa48210";
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public ResultLogin login(String name, String password) throws Exception {
        String privatePassword = AESUtil.encrypt(password, AES_TOKEN);
        Users user = usersMapper.findUserExist(name);
        if (user == null) return new ResultLogin(1000, "用户名不存在", null);
        Users user1 = usersMapper.login(name, privatePassword);
        if (user1 != null) {
            return new ResultLogin(1002, "登录成功", user1);

        }
        return new ResultLogin(1001, "密码错误", null);
    }

    @Override
    public ResultLogin register(String name, String password) throws Exception {
        Users user = usersMapper.findUserExist(name);
        if (user != null) return new ResultLogin(1000, "注册失败:用户名存在", null);
        String privatePassword = AESUtil.encrypt(password, AES_TOKEN);
        int register = usersMapper.register(name, privatePassword);
        if (register >= 0) {
            return new ResultLogin(1002, "注册成功", null);
        }
        return new ResultLogin(1001, "注册失败:原因未知", null);
    }
}
