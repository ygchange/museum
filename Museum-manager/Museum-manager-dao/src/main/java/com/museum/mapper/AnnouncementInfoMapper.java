package com.museum.mapper;

import com.museum.pojo.AnnouncementInfo;
import com.museum.pojo.AnnouncementInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnnouncementInfoMapper {
    int countByExample(AnnouncementInfoExample example);

    int deleteByExample(AnnouncementInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AnnouncementInfo record);

    int insertSelective(AnnouncementInfo record);

    List<AnnouncementInfo> selectByExample(AnnouncementInfoExample example);

    AnnouncementInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AnnouncementInfo record, @Param("example") AnnouncementInfoExample example);

    int updateByExample(@Param("record") AnnouncementInfo record, @Param("example") AnnouncementInfoExample example);

    int updateByPrimaryKeySelective(AnnouncementInfo record);

    int updateByPrimaryKey(AnnouncementInfo record);
}