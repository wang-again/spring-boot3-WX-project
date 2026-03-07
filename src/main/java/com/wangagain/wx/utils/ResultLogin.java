package com.wangagain.wx.utils;
// 封装返回结果的工具类
public class ResultLogin {
    private int code; // 状态码
    private String msg; // 返回信息
    private Object data; // 返回数据
    
    public ResultLogin() {
    }
    
    public ResultLogin(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    
    public int getCode() {
        return code;
    }
    
    public void setCode(int code) {
        this.code = code;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public Object getData() {
        return data;
    }
    
    public void setData(Object data) {
        this.data = data;
    }
}
