package com.wangagain.wx.controller.miniprogram;

import com.wangagain.wx.entity.miniprogram.Feedback;
import com.wangagain.wx.service.miniprogram.FeedbackService;
import com.wangagain.wx.utils.ResultLogin;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class FeedbackController {

    @Resource
    private FeedbackService feedbackService;

    /**
     * 提交举报反馈
     * @param userId
     * @param reportId
     * @param type
     * @param content
     * @return
     */
    @RequestMapping(value = "/submitFeedback", method = RequestMethod.POST)
    public ResultLogin submitFeedback(int userId, int reportId, String type, String content) {
        Feedback feedback = new Feedback();
        feedback.setUserId(userId);
        feedback.setReportId(reportId);
        feedback.setType(type);
        feedback.setContent(content);
        return feedbackService.submitFeedback(feedback);
    }

    /**
     * 根据ID查询反馈
     * @param fId
     * @return
     */
    @RequestMapping(value = "/getFeedbackById", method = RequestMethod.GET)
    public ResultLogin getFeedbackById(int fId) {
        return feedbackService.getFeedbackById(fId);
    }

    /**
     * 查询所有反馈
     * @return
     */
    @RequestMapping(value = "/getAllFeedbacks", method = RequestMethod.GET)
    public ResultLogin getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    /**
     * 更新反馈状态
     * @param fId
     * @param status
     * @param handler
     * @return
     */
    @RequestMapping(value = "/updateFeedbackStatus", method = RequestMethod.POST)
    public ResultLogin updateFeedbackStatus(int fId, String status, String handler) {
        Feedback feedback = new Feedback();
        feedback.setfId(fId);
        feedback.setStatus(status);
        feedback.setHandler(handler);
        return feedbackService.updateFeedbackStatus(feedback);
    }

    /**
     * 根据用户ID查询反馈
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getFeedbacksByUserId", method = RequestMethod.GET)
    public ResultLogin getFeedbacksByUserId(int userId) {
        return feedbackService.getFeedbacksByUserId(userId);
    }

    /**
     * 根据举报ID查询反馈
     * @param reportId
     * @return
     */
    @RequestMapping(value = "/getFeedbackByReportId", method = RequestMethod.GET)
    public ResultLogin getFeedbackByReportId(int reportId) {
        return feedbackService.getFeedbackByReportId(reportId);
    }

}