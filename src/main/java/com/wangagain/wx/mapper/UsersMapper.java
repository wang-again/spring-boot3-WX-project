package com.wangagain.wx.mapper;

// 接口

import com.wangagain.wx.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UsersMapper {

    /**
     * 登录功能
     * @param name
     * @return
     */
    public Users login(String name);


}
