package com.wangagain.wx.mapper.miniprogram;

import com.wangagain.wx.entity.miniprogram.WxUser;

public interface WxUserMapper {
    WxUser findByOpenid(String openid);
    int insert(WxUser user);
}