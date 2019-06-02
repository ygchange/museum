package com.museum.mapper;

import com.museum.pojo.ExhibitsType;
import com.museum.pojo.ExhibitsTypeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExhibitsTypeMapper {
    int countByExample(ExhibitsTypeExample example);

    int deleteByExample(ExhibitsTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExhibitsType record);

    int insertSelective(ExhibitsType record);

    List<ExhibitsType> selectByExample(ExhibitsTypeExample example);

    ExhibitsType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExhibitsType record, @Param("example") ExhibitsTypeExample example);

    int updateByExample(@Param("record") ExhibitsType record, @Param("example") ExhibitsTypeExample example);

    int updateByPrimaryKeySelective(ExhibitsType record);

    int updateByPrimaryKey(ExhibitsType record);
}