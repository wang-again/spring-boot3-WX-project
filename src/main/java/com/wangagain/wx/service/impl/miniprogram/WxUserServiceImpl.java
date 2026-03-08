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
            // 验证code参数
            if (code == null || code.isEmpty()) {
                return new ResultLogin(2000, "登录失败：缺少code参数", null);
            }
            
            // 调用微信API获取openid和session_key
            RestTemplate restTemplate = new RestTemplate();
            String url = String.format(WX_LOGIN_URL, APPID, APPSECRET, code);
            String response = restTemplate.getForObject(url, String.class);
            
            if (response == null) {
                return new ResultLogin(2000, "微信登录失败：无法获取响应", null);
            }
            
            JSONObject jsonObject = JSONObject.parseObject(response);
            
            // 检查微信API返回的错误
            if (jsonObject.containsKey("errcode")) {
                int errCode = jsonObject.getIntValue("errcode");
                String errMsg = jsonObject.getString("errmsg");
                return new ResultLogin(2000, "微信登录失败：" + errMsg, null);
            }

            String openid = jsonObject.getString("openid");
            if (openid == null || openid.isEmpty()) {
                return new ResultLogin(2000, "微信登录失败：无法获取openid", null);
            }

            // 生成token
            String token = TokenUtil.generateToken(openid);
            
            // 查询用户是否存在
            WxUser user = wxUserMapper.findByOpenid(openid);
            if (user == null) {
                // 创建新用户
                user = new WxUser();
                user.setOpenid(openid);
                user.setToken(token);
                user.setCreateTime(new Date());
                int insertResult = wxUserMapper.insert(user);
                if (insertResult <= 0) {
                    return new ResultLogin(2000, "微信登录失败：用户创建失败", null);
                }
            } else {
                // 更新现有用户的token
                user.setToken(token);
            }

            return new ResultLogin(2001, "登录成功", user);
        } catch (org.springframework.web.client.RestClientException e) {
            // 网络异常
            e.printStackTrace();
            return new ResultLogin(2002, "登录异常：网络连接失败", null);
        } catch (com.alibaba.fastjson.JSONException e) {
            // JSON解析异常
            e.printStackTrace();
            return new ResultLogin(2002, "登录异常：数据解析失败", null);
        } catch (Exception e) {
            // 其他异常
            e.printStackTrace();
            return new ResultLogin(2002, "登录异常：系统内部错误", null);
        }
    }
}