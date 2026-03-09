package com.wangagain.wx.service.miniprogram;

import com.wangagain.wx.entity.miniprogram.WxReport;

public interface WxReportService {
    // 添加举报
    int addReport(String reporter, String target, String type, String content,String location,String media,String phone);
    int updateReportStatus(int id, String status);
    int updateReportPhone(int id, String phone);
}