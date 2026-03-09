package com.wangagain.wx.mapper.miniprogram;

import com.wangagain.wx.entity.miniprogram.WxReport;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface WxReportMapper {
    @Select("select * from report where content=#{content}")
    WxReport findReportIsExist(@Param("content") String content);
    @Select("select * from report where reporter= #{reporter}")
    WxReport findReportByReporter(@Param("reporter") String reporter);
    @Select("select * from report where id= #{id}")
    WxReport findReportById(@Param("id") int id);
    @Insert("insert into report(reporter,target,type,content,status) values(#{reporter},#{target},#{type},#{content},'pending'")
    int insertReport(@Param("reporter") String reporter, @Param("target") String target, @Param("type") String type, @Param("content") String content);
    @Update("update report set status=#{status} where id=#{id}")
    int updateReportStatus(@Param("id") int id, @Param("status") String status);
}
