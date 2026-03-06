package com.wangagain.wx.service;

import com.wangagain.wx.utils.ResultLogin;

public interface UsersService {

    /**
     * 登录功能
     * @param name
     * @param password
     * @return
     */
    public ResultLogin login(String name, String password);

}
