package com.wangagain.wx.controller.miniprogram;

import com.wangagain.wx.service.miniprogram.WxUserService;
import com.wangagain.wx.utils.ResultLogin;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/miniprogram")
public class WxProgramController {

    @Resource
    private WxUserService wxUserService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResultLogin login(String code) {
        try {
            System.out.println("微信登录code：" + code);
            // 调用服务层处理登录
            return wxUserService.login(code);
        } catch (Exception e) {
            // 捕获未处理的异常
            e.printStackTrace();
            return new ResultLogin(2002, "登录异常：系统内部错误", null);
        }
    }
}