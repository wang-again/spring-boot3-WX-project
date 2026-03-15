package com.wangagain.wx.controller.admin;

import com.wangagain.wx.entity.miniprogram.Feedback;
import com.wangagain.wx.service.miniprogram.FeedbackService;
import com.wangagain.wx.utils.ResultLogin;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/admin/feedback")
public class AdminFeedbackController {

    @Resource
    private FeedbackService feedbackService;

    /**
     * 查询所有反馈（管理端）
     * @return
     */
    @GetMapping("/getAllFeedbacks")
    public ResultLogin getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    /**
     * 根据ID查询反馈
     * @param fId
     * @return
     */
    @GetMapping("/getFeedbackById")
    public ResultLogin getFeedbackById(int fId) {
        return feedbackService.getFeedbackById(fId);
    }

    /**
     * 更新反馈状态
     * @param fId
     * @param status
     * @param handler
     * @return
     */
    @PostMapping("/updateFeedbackStatus")
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
    @GetMapping("/getFeedbacksByUserId")
    public ResultLogin getFeedbacksByUserId(int userId) {
        return feedbackService.getFeedbacksByUserId(userId);
    }

    /**
     * 根据举报ID查询反馈
     * @param reportId
     * @return
     */
    @GetMapping("/getFeedbackByReportId")
    public ResultLogin getFeedbackByReportId(int reportId) {
        return feedbackService.getFeedbackByReportId(reportId);
    }

}