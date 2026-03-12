package com.wangagain.wx.service.miniprogram;

import com.wangagain.wx.entity.miniprogram.WxReport;
import com.wangagain.wx.utils.ResultLogin;

import java.util.List;

public interface WxReportService {
    // 添加举报
    ResultLogin addReport(WxReport wxReport);
    ResultLogin updateReportStatus(WxReport wxReport);
    ResultLogin updateReportPhone(WxReport wxReport);
    // 根据用户ID查询举报
    java.util.List<WxReport> getReportsByUserId(int user_id);
    // 根据ID查询举报
    WxReport getReportById(int id);
}