package com.wangagain.wx.service.impl.admin;

import com.wangagain.wx.entity.Users;
import com.wangagain.wx.mapper.UsersMapper;
import com.wangagain.wx.service.admin.UsersService;
import com.wangagain.wx.utils.ResultLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
    private final String AES_TOKEN = "asdfa48210123456";
    @Autowired
    private UsersMapper usersMapper;
    
    @Override
    public ResultLogin login(String name, String password) throws Exception {
        try {
            Users user = usersMapper.findUserExist(name);
            // 暂时注释掉AES加密，使用明文密码验证
            // String privatePassword = AESUtil.encrypt(password, AES_TOKEN);
            String privatePassword = password;
            if(user == null){
                // 用户不存在
                return new ResultLogin(1000,"用户名不存在", null);
            }else{
                // 用户存在
                Users user1 = usersMapper.login(name,privatePassword);
                if(user1 == null){
                    // 密码错误
                    return new ResultLogin(1001,"密码错误", null);
                }
                return new ResultLogin(1002,"登录成功", user1);
            }
        } catch (Exception e) {
            // 数据库表不存在或其他错误
            System.out.println("登录错误: " + e.getMessage());
            return new ResultLogin(1003,"系统错误，请联系管理员", null);
        }
    }

    @Override
    public ResultLogin register(String name, String password) throws Exception {
        String privatePassword = password;
        int register = usersMapper.register(name, privatePassword);
        if(register == 0){
            return new ResultLogin(1000,"用户名存在", null);
        }
        return new ResultLogin(1001,"注册成功", null);
    }
}
