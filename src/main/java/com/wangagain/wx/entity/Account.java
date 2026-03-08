package com.wangagain.wx.entity;

public class Account {
    private int uId;
    private String uName;
    private String uPwd;
    
    public Account() {
    }
    
    public Account(int uId, String uName, String uPwd) {
        this.uId = uId;
        this.uName = uName;
        this.uPwd = uPwd;
    }
    
    public int getuId() {
        return uId;
    }
    
    public void setuId(int uId) {
        this.uId = uId;
    }
    
    public String getuName() {
        return uName;
    }
    
    public void setuName(String uName) {
        this.uName = uName;
    }
    
    public String getuPwd() {
        return uPwd;
    }
    
    public void setuPwd(String uPwd) {
        this.uPwd = uPwd;
    }
}