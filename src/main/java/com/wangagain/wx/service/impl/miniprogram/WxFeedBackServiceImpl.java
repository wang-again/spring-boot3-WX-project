
package com.wangagain.wx.service.impl.miniprogram;

import com.wangagain.wx.mapper.miniprogram.WxFeedBackMapper;
import com.wangagain.wx.mapper.miniprogram.WxReportMapper;
import com.wangagain.wx.service.miniprogram.WxFeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class WxFeedBackServiceImpl implements WxFeedBackService {
    @Autowired
    private WxFeedBackMapper wxFeedBackMapper;
    @Autowired
    private WxReportMapper wxReportMapper;

    @Override
    public int addFeedBack(int report_id, String content) {
        try {
            if (wxReportMapper.findReportById(report_id) == null)
                return -1; // 举报不存在
            if (wxFeedBackMapper.findFeedBackByReportId(report_id) != null)
                return -4; // 已反馈
            int rows = wxFeedBackMapper.insertFeedBack(report_id, content);
            return rows > 0 ? 0 : -2;
        } catch (Exception e) {
            return -2;//其他错误
        }
    }
}

