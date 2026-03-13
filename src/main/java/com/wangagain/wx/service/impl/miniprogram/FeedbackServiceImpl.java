package com.wangagain.wx.service.impl.miniprogram;

import com.wangagain.wx.entity.miniprogram.Feedback;
import com.wangagain.wx.mapper.miniprogram.FeedbackMapper;
import com.wangagain.wx.service.miniprogram.FeedbackService;
import com.wangagain.wx.utils.ResultLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public ResultLogin submitFeedback(Feedback feedback) {
        try {
            // 设置默认状态和创建时间
            feedback.setStatus("待处理");
            feedback.setCreateTime(new Date());
            int result = feedbackMapper.addFeedback(feedback);
            if (result > 0) {
                return new ResultLogin(1001, "提交成功", feedback);
            } else {
                return new ResultLogin(1000, "提交失败", null);
            }
        } catch (Exception e) {
            System.out.println("系统错误：" + e.getMessage());
            e.printStackTrace();
            return new ResultLogin(1002, "系统错误: " + e.getMessage(), null);
        }
    }

    @Override
    public ResultLogin getFeedbackById(int fId) {
        try {
            Feedback feedback = feedbackMapper.getFeedbackById(fId);
            if (feedback != null) {
                return new ResultLogin(1001, "查询成功", feedback);
            } else {
                return new ResultLogin(1000, "反馈不存在", null);
            }
        } catch (Exception e) {
            return new ResultLogin(1002, "系统错误", null);
        }
    }

    @Override
    public ResultLogin getAllFeedbacks() {
        try {
            List<Feedback> feedbacks = feedbackMapper.getAllFeedbacks();
            return new ResultLogin(1001, "查询成功", feedbacks);
        } catch (Exception e) {
            System.out.println("系统错误：" + e.getMessage());
            e.printStackTrace();
            return new ResultLogin(1002, "系统错误: " + e.getMessage(), null);
        }
    }

    @Override
    public ResultLogin updateFeedbackStatus(Feedback feedback) {
        try {
            // 设置处理时间
            feedback.setHandleTime(new Date());
            int result = feedbackMapper.updateFeedbackStatus(feedback);
            if (result > 0) {
                return new ResultLogin(1001, "更新成功", feedback);
            } else {
                return new ResultLogin(1000, "更新失败", null);
            }
        } catch (Exception e) {
            return new ResultLogin(1002, "系统错误", null);
        }
    }

    @Override
    public ResultLogin getFeedbacksByUserId(int userId) {
        try {
            List<Feedback> feedbacks = feedbackMapper.getFeedbacksByUserId(userId);
            return new ResultLogin(1001, "查询成功", feedbacks);
        } catch (Exception e) {
            return new ResultLogin(1002, "系统错误", null);
        }
    }

    @Override
    public ResultLogin getFeedbackByReportId(int reportId) {
        try {
            Feedback feedback = feedbackMapper.getFeedbackByReportId(reportId);
            if (feedback != null) {
                return new ResultLogin(1001, "查询成功", feedback);
            } else {
                return new ResultLogin(1000, "反馈不存在", null);
            }
        } catch (Exception e) {
            return new ResultLogin(1002, "系统错误", null);
        }
    }

}