package com.wangagain.wx.service.miniprogram;

import com.wangagain.wx.entity.miniprogram.WxReport;
import com.wangagain.wx.utils.ResultLogin;

import java.util.List;

public interface WxReportService {
    ResultLogin addReport(WxReport wxReport);
    ResultLogin updateReportStatus(WxReport wxReport);
    ResultLogin updateReportPhone(WxReport wxReport);
    ResultLogin getReportsByReporter(String reporter);
    ResultLogin getReportById(int id);
}