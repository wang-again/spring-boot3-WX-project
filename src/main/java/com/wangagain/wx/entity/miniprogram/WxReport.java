package com.wangagain.wx.entity.miniprogram;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WxReport {
    private int id;
    private String report;
    private Date time;

}
