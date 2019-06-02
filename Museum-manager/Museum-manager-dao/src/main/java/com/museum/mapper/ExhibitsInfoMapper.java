package com.museum.mapper;

import com.museum.pojo.ExhibitsInfo;
import com.museum.pojo.ExhibitsInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExhibitsInfoMapper {
    int countByExample(ExhibitsInfoExample example);

    int deleteByExample(ExhibitsInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExhibitsInfo record);

    int insertSelective(ExhibitsInfo record);

    List<ExhibitsInfo> selectByExample(ExhibitsInfoExample example);

    ExhibitsInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExhibitsInfo record, @Param("example") ExhibitsInfoExample example);

    int updateByExample(@Param("record") ExhibitsInfo record, @Param("example") ExhibitsInfoExample example);

    int updateByPrimaryKeySelective(ExhibitsInfo record);

    int updateByPrimaryKey(ExhibitsInfo record);
}