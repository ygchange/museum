package com.museum.mapper;

import com.museum.pojo.EssayInfo;
import com.museum.pojo.EssayInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EssayInfoMapper {
    int countByExample(EssayInfoExample example);

    int deleteByExample(EssayInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EssayInfo record);

    int insertSelective(EssayInfo record);

    List<EssayInfo> selectByExampleWithBLOBs(EssayInfoExample example);

    List<EssayInfo> selectByExample(EssayInfoExample example);

    EssayInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EssayInfo record, @Param("example") EssayInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") EssayInfo record, @Param("example") EssayInfoExample example);

    int updateByExample(@Param("record") EssayInfo record, @Param("example") EssayInfoExample example);

    int updateByPrimaryKeySelective(EssayInfo record);

    int updateByPrimaryKeyWithBLOBs(EssayInfo record);

    int updateByPrimaryKey(EssayInfo record);
}