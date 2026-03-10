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
    private final String []STATUS_CHECK={"pending","processing","completed"};
    @Override
    public ResultLogin addReport(WxReport wxReport) {
        try {
            if (wxReportMapper.findReportIsExist(wxReport.getContent())!= null) return new ResultLogin(1001,"内容存在",null);//内容存在
            int rows = wxReportMapper.insertReport(wxReport.getUser_id(), wxReport.getTarget_name(), wxReport.getPhone(), wxReport.getType(), wxReport.getContent());
            if (rows > 0) return new ResultLogin(1000,"提交成功",wxReport);
        } catch (Exception e) {
            return new ResultLogin(1002,"服务器故障",null);//服务器故障
        }
        return new ResultLogin(1003,"其他原因",null);//其他原因
    }

    @Override
    public ResultLogin updateReportStatus(WxReport wxReport) {
        try {
            boolean flagCheck=true;
            for (String s : STATUS_CHECK) {
                if (s.equals(wxReport.getStatus())) {
                    flagCheck = false;
                    break;
                }
            }
            if(flagCheck)return new ResultLogin(1004,"状态输入无效",null);//状态输入无效
            if (wxReportMapper.findReportById(wxReport.getId()) == null) return new ResultLogin(1005,"内容不存在",null);//内容不存在
            int rows = wxReportMapper.updateReportStatus(wxReport.getId(), wxReport.getStatus());
            if (rows > 0) return new ResultLogin(1000,"修改成功",wxReport); //修改成功
        } catch (Exception e) {
            return new ResultLogin(1002,"服务器故障",null);//服务器故障
        }
        return new ResultLogin(1003,"其他原因",null);//其他原因
    }

    @Override
    public ResultLogin updateReportPhone(WxReport wxReport) {
        try {
            if (wxReportMapper.findReportById(wxReport.getId()) == null) return new ResultLogin(1005,"内容不存在",null);//内容不存在
            int rows = wxReportMapper.updateReportPhone(wxReport.getId(), wxReport.getPhone());
            if (rows > 0) return new ResultLogin(1001,"修改成功",wxReport) ;//修改成功
        } catch (Exception e) {
            return new ResultLogin(1002,"服务器故障",null);//服务器故障
        }
        return new ResultLogin(1003,"服务器故障",null);//其他原因
    }
}
