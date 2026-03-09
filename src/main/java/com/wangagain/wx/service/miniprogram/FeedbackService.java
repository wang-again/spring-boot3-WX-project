package com.wangagain.wx.service.miniprogram;

import com.wangagain.wx.entity.miniprogram.Feedback;
import com.wangagain.wx.utils.ResultLogin;

public interface FeedbackService {

    /**
     * 提交举报反馈
     * @param feedback
     * @return
     */
    public ResultLogin submitFeedback(Feedback feedback);

    /**
     * 根据ID查询反馈
     * @param fId
     * @return
     */
    public ResultLogin getFeedbackById(int fId);

    /**
     * 查询所有反馈
     * @return
     */
    public ResultLogin getAllFeedbacks();

    /**
     * 更新反馈状态
     * @param feedback
     * @return
     */
    public ResultLogin updateFeedbackStatus(Feedback feedback);

    /**
     * 根据用户ID查询反馈
     * @param userId
     * @return
     */
    public ResultLogin getFeedbacksByUserId(int userId);

}