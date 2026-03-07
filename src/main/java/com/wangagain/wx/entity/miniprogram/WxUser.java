package com.wangagain.wx.entity.miniprogram;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxUser {
    private int id;
    private String openid;
    private String token;
    private Date createTime;
}