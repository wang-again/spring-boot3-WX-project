package com.wangagain.wx.service.admin;

import com.wangagain.wx.utils.ResultLogin;

public interface UsersService {
    ResultLogin login(String name, String password) throws Exception;
    ResultLogin register(String name, String password) throws Exception;
}