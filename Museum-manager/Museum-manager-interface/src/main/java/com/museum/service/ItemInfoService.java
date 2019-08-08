package com.museum.service;

import com.museum.common.pojo.PageHelperResult;
import com.museum.custom.MemberInfoCustom;
import com.museum.pojo.ExhibitsInfo;
import com.museum.pojo.ExhibitsType;
import com.museum.pojo.SelectLog;

import java.util.List;
import java.util.Map;

public interface ItemInfoService {
    PageHelperResult getItemInfoList(Integer page, Integer rows,Integer itemType, String itemName,String bucketHostName);
    ExhibitsInfo insertItemInfo(ExhibitsInfo exhibitsInfo) ;
    List<ExhibitsType> selectItemType() ;
    Integer deleteItemInfoById(Integer id);

    Integer updateItemInfoById(ExhibitsInfo exhibitsInfo);

    ExhibitsInfo getExhibitsInfoById(Integer id);
    void updateItemInfoAndInsertSelectLog(ExhibitsInfo exhibitsInfo, SelectLog selectLog);
    List<ExhibitsInfo> getExhibitsInfo(Integer itemType,String itemName);
    List<ExhibitsInfo> getExhibitsInfo();
}
