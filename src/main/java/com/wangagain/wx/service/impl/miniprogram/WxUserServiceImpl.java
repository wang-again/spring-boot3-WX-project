package com.wangagain.wx.service.impl.miniprogram;

import com.alibaba.fastjson.JSONObject;
import com.wangagain.wx.entity.miniprogram.WxUser;
import com.wangagain.wx.mapper.miniprogram.WxUserMapper;
import com.wangagain.wx.service.miniprogram.WxUserService;
import com.wangagain.wx.utils.ResultLogin;
import com.wangagain.wx.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class WxUserServiceImpl implements WxUserService {

    @Autowired
    private WxUserMapper wxUserMapper;

    private final String APPID = "your_appid";
    private final String APPSECRET = "your_appsecret";
    private final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    @Override
    public ResultLogin login(String code) {
        try {
            // 调用微信API获取openid和session_key
            RestTemplate restTemplate = new RestTemplate();
            String url = String.format(WX_LOGIN_URL, APPID, APPSECRET, code);
            String response = restTemplate.getForObject(url, String.class);
            JSONObject jsonObject = JSONObject.parseObject(response);

            String openid = jsonObject.getString("openid");
            if (openid == null) {
                return new ResultLogin(2000, "微信登录失败", null);
            }

            // 查询用户是否存在
            WxUser user = wxUserMapper.findByOpenid(openid);
            if (user == null) {
                // 创建新用户
                user = new WxUser();
                user.setOpenid(openid);
                user.setCreateTime(new Date());
                wxUserMapper.insert(user);
            }

            // 生成token
            String token = TokenUtil.generateToken(openid);
            user.setToken(token);

            return new ResultLogin(2001, "登录成功", user);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultLogin(2002, "登录异常", null);
        }
    }
}