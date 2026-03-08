package com.wangagain.wx.mapper.miniprogram;

import com.wangagain.wx.entity.miniprogram.WxReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface WxReportMapper {
    @Select("select id")
    WxReport findReportIsExist(String report);
}
