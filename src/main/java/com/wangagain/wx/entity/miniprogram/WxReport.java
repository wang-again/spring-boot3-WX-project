package com.wangagain.wx.entity.miniprogram;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WxReport {
    private int id; // 举报id
    private String reporter;    // 举报人
    private String type;    // 举报类型
    private String content; // 举报内容
    private String status;  // 状态
    private LocalDateTime create_time;// 创建时间
    private String location;
    private String media;
    private String phone;
}
