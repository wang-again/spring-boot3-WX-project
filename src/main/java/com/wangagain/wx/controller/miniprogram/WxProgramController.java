package com.wangagain.wx.controller.miniprogram;

import com.wangagain.wx.entity.miniprogram.WxReport;
import com.wangagain.wx.service.miniprogram.WxReportService;
import com.wangagain.wx.service.miniprogram.WxUserService;
import com.wangagain.wx.utils.ResultLogin;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/miniprogram")
public class WxProgramController {

    @Resource
    private WxUserService wxUserService;

    @Resource
    private WxReportService wxReportService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResultLogin login(String code, String nickname) {
        try {
            System.out.println("微信登录code：" + code);
            System.out.println("微信用户昵称：" + nickname);
            // 调用服务层处理登录
            return wxUserService.login(code, nickname);
        } catch (Exception e) {
            // 捕获未处理的异常
            e.printStackTrace();
            return new ResultLogin(2002, "登录异常：系统内部错误", null);
        }
    }

    @RequestMapping(value = "/getMyReports", method = RequestMethod.GET)
    public ResultLogin getMyReports(int user_id) {
        try {
            List<WxReport> reports = wxReportService.getReportsByUserId(user_id);
            if (reports != null) {
                return new ResultLogin(1001, "查询成功", reports);
            } else {
                return new ResultLogin(1000, "查询失败", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultLogin(1002, "系统错误", null);
        }
    }

    @RequestMapping(value = "/getReportById", method = RequestMethod.GET)
    public ResultLogin getReportById(int id) {
        try {
            WxReport report = wxReportService.getReportById(id);
            if (report != null) {
                return new ResultLogin(1001, "查询成功", report);
            } else {
                return new ResultLogin(1000, "举报记录不存在", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultLogin(1002, "系统错误", null);
        }
    }

    @RequestMapping(value = "/addReport", method = RequestMethod.POST)
    public ResultLogin addReport(@org.springframework.web.bind.annotation.RequestBody WxReport wxReport) {
        try {
            if (wxReport.getUser_id() == null) {
                return new ResultLogin(1003, "用户ID不能为空", null);
            }
            return wxReportService.addReport(wxReport);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultLogin(1002, "系统错误", null);
        }
    }
}