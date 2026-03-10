package com.wangagain.wx.mapper.miniprogram;

import com.wangagain.wx.entity.miniprogram.WxReport;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface WxReportMapper {
    @Select("select * from report where content=#{content}")
    WxReport findReportIsExist(@Param("content") String content);
    @Select("select * from report where user_id= #{user_id}")
    WxReport findReportByReporter(@Param("user_id") int user_id);
    @Select("select * from report where id= #{id}")
    WxReport findReportById(@Param("id") int id);
    @Insert("insert into report(user_id,target_name,phone,type,content,status) values(#{user_id},#{target_name},#{phone},#{type},#{content},'pending')")
    int insertReport(@Param("user_id") int user_id, @Param("target_name") String target_name,@Param("phone") String phone ,@Param("type") String type, @Param("content") String content);
    @Update("update report set status=#{status} where id=#{id}")
    int updateReportStatus(@Param("id") int id, @Param("status") String status);
    @Update("update report set phone=#{phone} where id=#{id}")
    int updateReportPhone(@Param("id") int id,@Param("phone") String phone);

}
