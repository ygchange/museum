package com.museum.mapper;

import com.museum.pojo.ServiceCentre;
import com.museum.pojo.ServiceCentreExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ServiceCentreMapper {
    int countByExample(ServiceCentreExample example);

    int deleteByExample(ServiceCentreExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ServiceCentre record);

    int insertSelective(ServiceCentre record);

    List<ServiceCentre> selectByExample(ServiceCentreExample example);

    ServiceCentre selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ServiceCentre record, @Param("example") ServiceCentreExample example);

    int updateByExample(@Param("record") ServiceCentre record, @Param("example") ServiceCentreExample example);

    int updateByPrimaryKeySelective(ServiceCentre record);

    int updateByPrimaryKey(ServiceCentre record);
}