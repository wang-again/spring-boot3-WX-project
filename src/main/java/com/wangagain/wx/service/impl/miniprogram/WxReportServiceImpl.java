package com.wangagain.wx.service.impl.miniprogram;

import com.wangagain.wx.entity.miniprogram.WxReport;
import com.wangagain.wx.mapper.admin.AccountMapper;
import com.wangagain.wx.mapper.miniprogram.WxReportMapper;
import com.wangagain.wx.service.miniprogram.WxReportService;
import com.wangagain.wx.utils.ResultLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
                return new ResultLogin(1001, "内存存在", null);//内容存在
            if (accountMapper.findUserExist(wxReport.getReporter()) == null)
                return new ResultLogin(1003, "用户名不存在", null);//用户不存在
            int rows = wxReportMapper.insertReport(wxReport.getReporter(), wxReport.getType(), wxReport.getContent(), wxReport.getLocation(), wxReport.getMedia(), wxReport.getPhone());
            if (rows > 0) return new ResultLogin(1000, "提交陈工", wxReport);
        } catch (Exception e) {
            return new ResultLogin(1004, "服务器故障", null);//服务器故障
        }
        return new ResultLogin(1005, "其他原因", null);//其他原因
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
            if (flagCheck) return new ResultLogin(1006, "状态不存在", null);//状态输入无效
            if (wxReportMapper.findReportById(wxReport.getId()) == null)
                return new ResultLogin(1002, "内容不存在", null);//内容不存在
            int rows = wxReportMapper.updateReportStatus(wxReport.getId(), wxReport.getStatus());
            if (rows > 0) return new ResultLogin(1000, "修改成功", wxReport); //修改成功
        } catch (Exception e) {
            return new ResultLogin(1004, "服务器故障", null);//服务器故障
        }
        return new ResultLogin(1005, "其他原因", null);//其他原因
    }

    @Override
    public ResultLogin updateReportPhone(WxReport wxReport) {
        try {
            if (wxReportMapper.findReportById(wxReport.getId()) == null)
                return new ResultLogin(1002, "内容不存在", null);//内容不存在
            int rows = wxReportMapper.updateReportPhone(wxReport.getId(), wxReport.getPhone());
            if (rows > 0) return new ResultLogin(1000, "修改成功", wxReport);//修改成功
        } catch (Exception e) {
            return new ResultLogin(1004, "服务器故障", null);//服务器故障
        }
        return new ResultLogin(1005, "其他原因", null);//其他原因
    }
}
