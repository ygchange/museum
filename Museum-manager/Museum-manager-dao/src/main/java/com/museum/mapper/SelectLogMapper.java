package com.museum.mapper;

import com.museum.pojo.SelectLog;
import com.museum.pojo.SelectLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SelectLogMapper {
    int countByExample(SelectLogExample example);

    int deleteByExample(SelectLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SelectLog record);

    int insertSelective(SelectLog record);

    List<SelectLog> selectByExample(SelectLogExample example);

    SelectLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SelectLog record, @Param("example") SelectLogExample example);

    int updateByExample(@Param("record") SelectLog record, @Param("example") SelectLogExample example);

    int updateByPrimaryKeySelective(SelectLog record);

    int updateByPrimaryKey(SelectLog record);
}