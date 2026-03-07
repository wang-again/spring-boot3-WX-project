package com.wangagain.wx.utils;

// 封装返回结果的工具类

import lombok.Data;

@Data
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

}
