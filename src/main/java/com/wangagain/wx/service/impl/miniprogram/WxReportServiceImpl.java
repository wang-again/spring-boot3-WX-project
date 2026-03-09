package com.wangagain.wx.service.impl.miniprogram;

import com.wangagain.wx.entity.miniprogram.WxReport;
import com.wangagain.wx.mapper.admin.AccountMapper;
import com.wangagain.wx.mapper.miniprogram.WxReportMapper;
import com.wangagain.wx.service.miniprogram.WxReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxReportServiceImpl implements WxReportService {
    @Autowired
    private WxReportMapper wxReportMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public int addReport(String reporter, String target, String type, String content) {
        try {
            if (wxReportMapper.findReportIsExist(content) != null) return -1;//内容存在
            if (accountMapper.findUserExist(reporter) == null) return -2;//用户不存在
            int rows = wxReportMapper.insertReport(reporter, target, type, content);
            if (rows > 0) return 0;
        }catch(Exception e){
            return -3;//服务器故障
        }
        return -4;//其他原因
    }

    @Override
    public int updateReportStatus(int id, String status) {
        try {
            if (wxReportMapper.findReportById(id) != null) return -1;//内容不存在
            int rows = wxReportMapper.updateReportStatus(id, status);
            if (rows > 0) return 0;
        }catch(Exception e){
            return -3;//服务器故障
        }
        return -4;//其他原因
    }
    }
