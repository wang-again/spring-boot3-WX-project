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
    private String reporter;
    private String target;
    private String type;
    private String content;
    private String status;
    private Date create_time;

}
