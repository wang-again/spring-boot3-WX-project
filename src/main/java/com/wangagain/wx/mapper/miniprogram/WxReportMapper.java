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
    WxReport findReportByUserId(@Param("user_id") int user_id);
    @Select("select * from report where id= #{id}")
    WxReport findReportById(@Param("id") int id);
    @Select("select * from report where user_id= #{user_id} order by create_time desc")
    java.util.List<WxReport> findReportsByUserId(@Param("user_id") int user_id);
    @Insert("insert into report(user_id,type,content,status,location,media,phone) values(#{user_id},#{type},#{content},'pending',#{location},#{media},#{phone})")
    int insertReport(@Param("user_id") int user_id,@Param("type") String type, @Param("content") String content,@Param("location") String location, @Param("media") String media, @Param("phone") String phone);
    @Update("update report set status=#{status} where id=#{id}")
    int updateReportStatus(@Param("id") int id, @Param("status") String status);
    @Update("update report set phone=#{phone} where id=#{id}")
    int updateReportPhone(@Param("id") int id,@Param("phone") String phone);
    
    @Select("select * from report order by create_time desc")
    java.util.List<WxReport> findAllReports();

}
