package com.museum.service;

import com.museum.common.pojo.PageHelperResult;
import com.museum.custom.MemberInfoCustom;
import com.museum.pojo.ExhibitsInfo;
import com.museum.pojo.ExhibitsType;

import java.util.List;

public interface ItemInfoService {
    PageHelperResult getItemInfoList(Integer page, Integer rows);
    void insertItemInfo(ExhibitsInfo exhibitsInfo) ;
    List<ExhibitsType> selectItemType() ;
    Integer deleteItemInfo(Integer id);

    Integer updateItemInfo(ExhibitsInfo exhibitsInfo);

    ExhibitsInfo selectExhibitsInfoById(Integer id);
}
