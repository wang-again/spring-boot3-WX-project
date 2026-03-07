package com.wangagain.wx.mapper;

// 接口

import com.wangagain.wx.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UsersMapper {

    /**
     * 登录功能
     * @param name
     * @return
     */
    Users login(@Param("uName") String name, @Param("uPwd") String password);
    int register(@Param("uName") String name, @Param("uPwd") String password);
    Users findUserExist(@Param("uName") String name);
}
