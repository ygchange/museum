package com.museum.service;

import com.museum.common.pojo.PageHelperResult;
import com.museum.pojo.EssayInfo;

import java.util.List;

public interface EssayInfoService {
    void insertEssayInfo(EssayInfo essayInfo);

    PageHelperResult getEssayInfo(Integer page, Integer rows, String bucketHostName);

    Integer updateEssayInfoById(EssayInfo essayInfo);

    Integer deleteEssayInfoById(Integer id);

   List<EssayInfo> getLatestEssayInfo(Integer essayType);

    List<EssayInfo> getHotEssayInfo(Integer essayType);
    List<EssayInfo> getHotEssayInfo();

    List<EssayInfo> getDynamicOrCulture(Integer essayType);

    PageHelperResult getDynamicOrCultureByPage(Integer essayType, Integer page, Integer rows);

    EssayInfo getDynamicOrCultureById(Integer id);
}
