package com.wangagain.wx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private int uId;
    private String uName;
    private String uPwd;
}
