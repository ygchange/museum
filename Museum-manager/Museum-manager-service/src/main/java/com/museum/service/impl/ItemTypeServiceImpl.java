package com.museum.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.museum.common.pojo.PageHelperResult;
import com.museum.mapper.ExhibitsTypeMapper;
import com.museum.pojo.*;
import com.museum.service.ItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemTypeServiceImpl implements ItemTypeService {
    @Autowired
    private ExhibitsTypeMapper exhibitsTypeMapper;
    @Override
    //查询商品类型
    public PageHelperResult getItemTypeList(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        ExhibitsTypeExample example=new ExhibitsTypeExample();
        List<ExhibitsType> list = exhibitsTypeMapper.selectByExample(example);
        PageInfo<ExhibitsType> pageInfo=new PageInfo<>(list);
        PageHelperResult result=new PageHelperResult();
        result.setPages(pageInfo.getPages());
        result.setRows(list);
        result.setTotal((int) pageInfo.getTotal());
        return result;

    }
    //判断商品类型是否存在
    @Override
    public ExhibitsType selectItemTypeByUserName(String typeName) {
        ExhibitsTypeExample example =new ExhibitsTypeExample();
        ExhibitsTypeExample.Criteria criteria = example.createCriteria();
        criteria.andTypeNameEqualTo(typeName);
        List<ExhibitsType> exhibitsTypes = exhibitsTypeMapper.selectByExample(example);
        if(exhibitsTypes.size()!=0){
            ExhibitsType exhibitsType=exhibitsTypes.get(0);
            return exhibitsType;
        }
        return null;
    }
    //添加商品类型
    @Override
    public void insertItemType(ExhibitsType exhibitsType) throws Exception {
        exhibitsTypeMapper.insertSelective(exhibitsType);
    }


}
