package com.wangagain.wx.controller.admin;

import com.wangagain.wx.entity.miniprogram.WxReport;
import com.wangagain.wx.service.miniprogram.WxReportService;
import com.wangagain.wx.utils.ResultLogin;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/admin/report")
public class AdminReportController {

    @Resource
    private WxReportService wxReportService;

    /**
     * 查询所有举报记录（管理端）
     * @return
     */
    @GetMapping("/getAllReports")
    public ResultLogin getAllReports() {
        try {
            return wxReportService.getAllReports();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultLogin(1002, "系统错误", null);
        }
    }

    /**
     * 更新举报状态
     * @param id
     * @param status
     * @return
     */
    @PostMapping("/updateReportStatus")
    public ResultLogin updateReportStatus(int id, String status) {
        try {
            WxReport wxReport = new WxReport();
            wxReport.setId(id);
            wxReport.setStatus(status);
            return wxReportService.updateReportStatus(wxReport);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultLogin(1002, "系统错误", null);
        }
    }

    /**
     * 更新举报联系电话
     * @param id
     * @param phone
     * @return
     */
    @PostMapping("/updateReportPhone")
    public ResultLogin updateReportPhone(int id, String phone) {
        try {
            WxReport wxReport = new WxReport();
            wxReport.setId(id);
            wxReport.setPhone(phone);
            return wxReportService.updateReportPhone(wxReport);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultLogin(1002, "系统错误", null);
        }
    }

}