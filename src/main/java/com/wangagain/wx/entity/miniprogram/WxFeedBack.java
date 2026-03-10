package com.wangagain.wx.entity.miniprogram;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxFeedBack {
    private int id;
    private int report_id;
    private String content;
    private LocalDateTime create_time;
}