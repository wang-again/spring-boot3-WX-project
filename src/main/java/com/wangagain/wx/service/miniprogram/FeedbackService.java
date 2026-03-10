package com.wangagain.wx.service.miniprogram;

import com.wangagain.wx.entity.miniprogram.Feedback;
import com.wangagain.wx.utils.ResultLogin;

public interface FeedbackService {

    public ResultLogin submitFeedback(Feedback feedback);

    public ResultLogin getFeedbackById(int id);

    public ResultLogin getAllFeedbacks();

    public ResultLogin updateFeedback(Feedback feedback);

    public ResultLogin getFeedbacksByReportId(int reportId);

}
