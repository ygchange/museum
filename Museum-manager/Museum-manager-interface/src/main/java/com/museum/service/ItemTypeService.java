package com.museum.service;

import com.museum.common.pojo.PageHelperResult;
import com.museum.pojo.ExhibitsInfo;
import com.museum.pojo.ExhibitsType;

public interface ItemTypeService {
    //查询商品类型
    PageHelperResult getItemTypeList(Integer page, Integer rows);

    ExhibitsType selectItemTypeByUserName(String typeName);

    void insertItemType(ExhibitsType exhibitsType) throws Exception;

    Integer updateItemTypeById(ExhibitsType exhibitsType);

    Integer deleteItemTypeById(Integer id);

    ExhibitsType selectItemTypeById(Integer typeId);
}
