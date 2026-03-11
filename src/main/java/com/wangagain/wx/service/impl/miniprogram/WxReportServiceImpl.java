package com.wangagain.wx.service.impl.miniprogram;
import com.wangagain.wx.entity.miniprogram.WxReport;
import com.wangagain.wx.mapper.admin.AccountMapper;
import com.wangagain.wx.mapper.miniprogram.WxReportMapper;
import com.wangagain.wx.service.miniprogram.WxReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxReportServiceImpl implements WxReportService {
    @Autowired
    private WxReportMapper wxReportMapper;
    @Autowired
    private AccountMapper accountMapper;
    private final String []STATUS_CHECK=new String[]{"pending","processing","completed"};
    @Override
    public int addReport(String reporter, String target, String type, String content, String location, String media, String phone) {
        try {
            if (wxReportMapper.findReportIsExist(content) != null) return -1;//内容存在
            if (accountMapper.findUserExist(reporter) == null) return -2;//用户不存在
            int rows = wxReportMapper.insertReport(reporter, target, type, content, location, media, phone);
            if (rows > 0) return 0;
        } catch (Exception e) {
            return -3;//服务器故障
        }
        return -4;//其他原因
    }

    @Override
    public int updateReportStatus(int id, String status) {
        try {
            boolean flagCheck=true;
            for (String s : STATUS_CHECK) {
                if (s.equals(status)) {
                    flagCheck = false;
                    break;
                }
            }
            if(flagCheck)return -5;//状态输入无效
            if (wxReportMapper.findReportById(id) == null) return -1;//内容不存在
            int rows = wxReportMapper.updateReportStatus(id, status);
            if (rows > 0) return 0; //修改成功
        } catch (Exception e) {
            return -3;//服务器故障
        }
        return -4;//其他原因
    }

    @Override
    public int updateReportPhone(int id, String phone) {
        try {
            if (wxReportMapper.findReportById(id) == null) return -1;//内容不存在
            int rows = wxReportMapper.updateReportPhone(id,phone);
            if (rows > 0) return 0;//修改成功
        } catch (Exception e) {
            return -3;//服务器故障
        }
        return -4;//其他原因
    }

    @Override
    public List<WxReport> getReportsByReporter(String reporter) {
        try {
            return wxReportMapper.findReportsByReporter(reporter);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public WxReport getReportById(int id) {
        try {
            return wxReportMapper.findReportById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
