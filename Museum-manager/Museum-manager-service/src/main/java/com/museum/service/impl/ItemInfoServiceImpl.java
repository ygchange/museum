package com.museum.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.museum.common.pojo.PageHelperResult;
import com.museum.custom.MemberInfoCustom;
import com.museum.mapper.CustomMapper;
import com.museum.mapper.ExhibitsInfoMapper;
import com.museum.mapper.ExhibitsTypeMapper;
import com.museum.pojo.ExhibitsInfo;
import com.museum.pojo.ExhibitsType;
import com.museum.pojo.ExhibitsTypeExample;
import com.museum.service.ItemInfoService;
import com.museum.service.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemInfoServiceImpl implements ItemInfoService {
    @Autowired
    private CustomMapper customMapper;
    @Autowired
    private ExhibitsInfoMapper exhibitsInfoMapper;
    @Autowired
    private ExhibitsTypeMapper exhibitsTypeMapper;
    //查询展品
    @Override
    public PageHelperResult getItemInfoList(Integer page,Integer rows) {
        PageHelper.startPage(page,rows);
        List<MemberInfoCustom> list = customMapper.selectExhibits();
        PageInfo<MemberInfoCustom> pageInfo=new PageInfo<>(list);
        PageHelperResult result=new PageHelperResult();
        result.setRows(list);
        result.setTotal((int) pageInfo.getTotal());
        return result;
    }
    //添加商品
    @Override
    public void insertItemInfo(ExhibitsInfo exhibitsInfo) {
        exhibitsInfoMapper.insertSelective(exhibitsInfo);
    }
    //查询商品类型
    @Override
    public List<ExhibitsType> selectItemType() {
        ExhibitsTypeExample example=new ExhibitsTypeExample();
        List<ExhibitsType> list = exhibitsTypeMapper.selectByExample(example);
        return list;
    }

    @Override
    public Integer deleteItemInfo(Integer id) {
        int i = exhibitsInfoMapper.deleteByPrimaryKey(id);

        return i;
    }

    @Override
    public Integer updateItemInfo(ExhibitsInfo exhibitsInfo) {
        int i = exhibitsInfoMapper.updateByPrimaryKeySelective(exhibitsInfo);
        return i;
    }
    //删除商品


}
