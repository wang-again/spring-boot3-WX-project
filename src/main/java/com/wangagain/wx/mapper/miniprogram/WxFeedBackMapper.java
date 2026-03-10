package com.wangagain.wx.mapper.miniprogram;

import com.wangagain.wx.entity.miniprogram.WxFeedBack;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface WxFeedBackMapper {

    // 根据举报ID查询反馈
    @Select("select * from feedback where report_id=#{report_id}")
    WxFeedBack findFeedBackByReportId(@Param("report_id") int report_id);

    // 根据ID查询
    @Select("select * from feedback where id=#{id}")
    WxFeedBack findFeedBackById(@Param("id") int id);

    // 添加反馈
    @Insert("insert into feedback(report_id,content,create_time) values(#{report_id},#{content},now())")
    int insertFeedBack(@Param("report_id") int report_id,
                       @Param("content") String content);
}