package com.wangagain.wx.service.miniprogram;

import com.wangagain.wx.entity.miniprogram.WxReport;
import com.wangagain.wx.utils.ResultLogin;

public interface WxReportService {
    // 添加举报
    ResultLogin addReport(WxReport wxReport);
    ResultLogin updateReportStatus(WxReport wxReport);
    ResultLogin updateReportPhone(WxReport wxReport);
}