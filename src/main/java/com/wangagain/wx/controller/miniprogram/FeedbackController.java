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

    @RequestMapping(value = "/submitFeedback", method = RequestMethod.POST)
    public ResultLogin submitFeedback(int reportId, String content) {
        Feedback feedback = new Feedback();
        feedback.setReportId(reportId);
        feedback.setContent(content);
        return feedbackService.submitFeedback(feedback);
    }

    @RequestMapping(value = "/getFeedbackById", method = RequestMethod.GET)
    public ResultLogin getFeedbackById(int id) {
        return feedbackService.getFeedbackById(id);
    }

    @RequestMapping(value = "/getAllFeedbacks", method = RequestMethod.GET)
    public ResultLogin getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @RequestMapping(value = "/updateFeedback", method = RequestMethod.POST)
    public ResultLogin updateFeedback(int id, String content) {
        Feedback feedback = new Feedback();
        feedback.setId(id);
        feedback.setContent(content);
        return feedbackService.updateFeedback(feedback);
    }

    @RequestMapping(value = "/getFeedbacksByReportId", method = RequestMethod.GET)
    public ResultLogin getFeedbacksByReportId(int reportId) {
        return feedbackService.getFeedbacksByReportId(reportId);
    }

}
