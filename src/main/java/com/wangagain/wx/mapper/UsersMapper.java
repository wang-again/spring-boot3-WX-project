package com.wangagain.wx.mapper;

// 接口

import com.wangagain.wx.entity.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UsersMapper {

    /**
     * 登录功能
     * @param name
     * @return
     */
    @Select("SELECT uId,uName,uPwd FROM account WHERE uName = #{uName} and uPwd=#{uPwd}")
    Users login(@Param("uName") String name, @Param("uPwd") String password);
    @Insert("insert into account(uName,uPwd) values(#{uName},#{uPwd})")
    int register(@Param("uName") String name, @Param("uPwd") String password);
    @Select("SELECT uId,uName,uPwd FROM account WHERE uName = #{uName}")
    Users findUserExist(@Param("uName") String name);
}
