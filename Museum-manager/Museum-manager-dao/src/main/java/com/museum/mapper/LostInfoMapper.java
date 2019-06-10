package com.museum.mapper;

import com.museum.pojo.LostInfo;
import com.museum.pojo.LostInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LostInfoMapper {
    int countByExample(LostInfoExample example);

    int deleteByExample(LostInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LostInfo record);

    int insertSelective(LostInfo record);

    List<LostInfo> selectByExample(LostInfoExample example);

    LostInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LostInfo record, @Param("example") LostInfoExample example);

    int updateByExample(@Param("record") LostInfo record, @Param("example") LostInfoExample example);

    int updateByPrimaryKeySelective(LostInfo record);

    int updateByPrimaryKey(LostInfo record);
}