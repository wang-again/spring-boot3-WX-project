package com.wangagain.wx.mapper.admin;

import com.wangagain.wx.entity.admin.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Mapper
public interface AccountMapper {

    /**
     * 登录功能
     * @param name
     * @param password
     * @return
     */
    Account login(@Param("uName") String name, @Param("uPwd") String password);
    int register(@Param("uName") String name, @Param("uPwd") String password);
    Account findUserExist(@Param("uName") String name);
    ArrayList<Account> allUsers();
}