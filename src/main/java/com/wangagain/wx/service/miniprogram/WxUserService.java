package com.wangagain.wx.service.miniprogram;

import com.wangagain.wx.utils.ResultLogin;

public interface WxUserService {
    ResultLogin login(String code);
}