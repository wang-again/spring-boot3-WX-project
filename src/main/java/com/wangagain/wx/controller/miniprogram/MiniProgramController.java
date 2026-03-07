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
public class MiniProgramController {

    @Resource
    private WxUserService wxUserService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResultLogin login(String code) {
        System.out.println("微信登录code：" + code);
        return wxUserService.login(code);
    }
}