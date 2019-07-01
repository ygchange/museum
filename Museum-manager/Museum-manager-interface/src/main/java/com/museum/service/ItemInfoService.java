package com.museum.service;

import com.museum.common.pojo.PageHelperResult;
import com.museum.custom.MemberInfoCustom;
import com.museum.pojo.ExhibitsInfo;
import com.museum.pojo.ExhibitsType;

import java.util.List;

public interface ItemInfoService {
    PageHelperResult getItemInfoList(Integer page, Integer rows);
    ExhibitsInfo insertItemInfo(ExhibitsInfo exhibitsInfo) ;
    List<ExhibitsType> selectItemType() ;
    Integer deleteItemInfoById(Integer id);

    Integer updateItemInfoById(ExhibitsInfo exhibitsInfo);

    ExhibitsInfo getExhibitsInfoById(Integer id);
}
