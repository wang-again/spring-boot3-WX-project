package com.wangagain.wx.service.impl.miniprogram;

import com.wangagain.wx.entity.miniprogram.WxReport;
import com.wangagain.wx.mapper.admin.AccountMapper;
import com.wangagain.wx.mapper.miniprogram.WxReportMapper;
import com.wangagain.wx.service.miniprogram.WxReportService;
import com.wangagain.wx.utils.ResultLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxReportServiceImpl implements WxReportService {
    @Autowired
    private WxReportMapper wxReportMapper;
    @Autowired
    private AccountMapper accountMapper;
    private final String[] STATUS_CHECK = new String[]{"pending", "processing", "completed"};

    @Override
    public ResultLogin addReport(WxReport wxReport) {
        try {
            if (wxReportMapper.findReportIsExist(wxReport.getContent()) != null)
                return new ResultLogin(1001, "内容存在", null);
            if (accountMapper.findUserExist(wxReport.getReporter()) == null)
                return new ResultLogin(1003, "用户名不存在", null);
            int rows = wxReportMapper.insertReport(wxReport.getReporter(), wxReport.getType(), wxReport.getContent(), wxReport.getLocation(), wxReport.getMedia(), wxReport.getPhone());
            if (rows > 0) return new ResultLogin(1000, "提交成功", wxReport);
        } catch (Exception e) {
            return new ResultLogin(1004, "服务器故障", null);
        }
        return new ResultLogin(1005, "其他原因", null);
    }

    @Override
    public ResultLogin updateReportStatus(WxReport wxReport) {
        try {
            boolean flagCheck = true;
            for (String s : STATUS_CHECK) {
                if (s.equals(wxReport.getStatus())) {
                    flagCheck = false;
                    break;
                }
            }
            if (flagCheck) return new ResultLogin(1006, "状态不存在", null);
            if (wxReportMapper.findReportById(wxReport.getId()) == null)
                return new ResultLogin(1002, "内容不存在", null);
            int rows = wxReportMapper.updateReportStatus(wxReport.getId(), wxReport.getStatus());
            if (rows > 0) return new ResultLogin(1000, "修改成功", wxReport);
        } catch (Exception e) {
            return new ResultLogin(1004, "服务器故障", null);
        }
        return new ResultLogin(1005, "其他原因", null);
    }

    @Override
    public ResultLogin updateReportPhone(WxReport wxReport) {
        try {
            if (wxReportMapper.findReportById(wxReport.getId()) == null)
                return new ResultLogin(1002, "内容不存在", null);
            int rows = wxReportMapper.updateReportPhone(wxReport.getId(), wxReport.getPhone());
            if (rows > 0) return new ResultLogin(1000, "修改成功", wxReport);
        } catch (Exception e) {
            return new ResultLogin(1004, "服务器故障", null);
        }
        return new ResultLogin(1005, "其他原因", null);
    }

    @Override
    public ResultLogin getReportsByReporter(String reporter) {
        try {
            List<WxReport> reports = wxReportMapper.findReportsByReporter(reporter);
            return new ResultLogin(1001, "查询成功", reports);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultLogin(1002, "系统错误", null);
        }
    }

    @Override
    public ResultLogin getReportById(int id) {
        try {
            WxReport report = wxReportMapper.findReportById(id);
            if (report != null) {
                return new ResultLogin(1001, "查询成功", report);
            } else {
                return new ResultLogin(1000, "举报记录不存在", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultLogin(1002, "系统错误", null);
        }
    }
}
