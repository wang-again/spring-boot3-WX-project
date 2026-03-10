package com.wangagain.wx.entity.miniprogram;

import java.util.Date;

public class WxUser {
    private int id;
    private String openid;
    private String token;
    private String nickname;
    private Date createTime;
    
    public WxUser() {
    }
    
    public WxUser(int id, String openid, String token, String nickname, Date createTime) {
        this.id = id;
        this.openid = openid;
        this.token = token;
        this.nickname = nickname;
        this.createTime = createTime;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getOpenid() {
        return openid;
    }
    
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}