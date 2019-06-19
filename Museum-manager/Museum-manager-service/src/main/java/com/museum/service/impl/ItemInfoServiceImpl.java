package com.museum.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.museum.common.pojo.PageHelperResult;
import com.museum.custom.ExhibitsInfoCustom;
import com.museum.custom.MemberInfoCustom;
import com.museum.mapper.CustomMapper;
import com.museum.mapper.ExhibitsInfoMapper;
import com.museum.mapper.ExhibitsTypeMapper;
import com.museum.mapper.MemberInfoMapper;
import com.museum.pojo.ExhibitsInfo;
import com.museum.pojo.ExhibitsType;
import com.museum.pojo.ExhibitsTypeExample;
import com.museum.pojo.MemberInfo;
import com.museum.service.ItemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemInfoServiceImpl implements ItemInfoService {
    @Autowired
    private CustomMapper customMapper;
    @Autowired
    private ExhibitsInfoMapper exhibitsInfoMapper;
    @Autowired
    private ExhibitsTypeMapper exhibitsTypeMapper;
    @Autowired
    private MemberInfoMapper memberInfoMapper;
    //查询展品
    @Override
    public PageHelperResult getItemInfoList(Integer page,Integer rows) {
        PageHelper.startPage(page,rows);
        List<ExhibitsInfoCustom> list = customMapper.selectExhibits();
        for (ExhibitsInfoCustom exhibitsInfo:list)
        {
            MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(exhibitsInfo.getOperatorId());
            if(memberInfo!=null){
                memberInfo.setPassword(null);
            }

            exhibitsInfo.setMemberInfo(memberInfo);
        }
        PageInfo<ExhibitsInfoCustom> pageInfo=new PageInfo<>(list);
        PageHelperResult result=new PageHelperResult();
        result.setRows(list);
        result.setTotal((int) pageInfo.getTotal());
        return result;
    }
    //添加商品
    @Override
    public void insertItemInfo(ExhibitsInfo exhibitsInfo) {
        exhibitsInfo.setAddTime(new Date());
        exhibitsInfoMapper.insertSelective(exhibitsInfo);
    }
    //查询商品类型
    @Override
    public List<ExhibitsType> selectItemType() {
        ExhibitsTypeExample example=new ExhibitsTypeExample();
        List<ExhibitsType> list = exhibitsTypeMapper.selectByExample(example);
        return list;
    }
    //删除展品
    @Override
    public Integer deleteItemInfoById(Integer id) {
        int i = exhibitsInfoMapper.deleteByPrimaryKey(id);

        return i;
    }

    @Override
    public Integer updateItemInfoById(ExhibitsInfo exhibitsInfo) {
        int i = exhibitsInfoMapper.updateByPrimaryKeySelective(exhibitsInfo);
        return i;
    }
    //根据展品id查询
    @Override
    public ExhibitsInfo selectExhibitsInfoById(Integer id) {
        ExhibitsInfo exhibitsInfo = exhibitsInfoMapper.selectByPrimaryKey(id);
        return exhibitsInfo;
    }




}
