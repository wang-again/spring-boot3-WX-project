package com.wangagain.wx.mapper.miniprogram;

import com.wangagain.wx.entity.miniprogram.Feedback;

import java.util.List;

public interface FeedbackMapper {
    // 添加反馈
    int addFeedback(Feedback feedback);
    
    // 根据ID查询反馈
    Feedback getFeedbackById(int fId);
    
    // 查询所有反馈
    List<Feedback> getAllFeedbacks();
    
    // 更新反馈状态
    int updateFeedbackStatus(Feedback feedback);
    
    // 根据用户ID查询反馈
    List<Feedback> getFeedbacksByUserId(int userId);
    
    // 根据举报ID查询反馈
    Feedback getFeedbackByReportId(int reportId);
}