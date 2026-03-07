package com.wangagain.wx.entity.miniprogram;

import java.util.Date;

public class WxUser {
    private int id;
    private String openid;
    private String token;
    private Date createTime;
    
    public WxUser() {
    }
    
    public WxUser(int id, String openid, String token, Date createTime) {
        this.id = id;
        this.openid = openid;
        this.token = token;
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
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}