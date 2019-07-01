package com.museum.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.museum.common.pojo.PageHelperResult;
import com.museum.common.utils.CodeUploadUtil;
import com.museum.custom.ExhibitsInfoCustom;
import com.museum.custom.MemberInfoCustom;
import com.museum.mapper.CustomMapper;
import com.museum.mapper.ExhibitsInfoMapper;
import com.museum.mapper.ExhibitsTypeMapper;
import com.museum.mapper.MemberInfoMapper;
import com.museum.pojo.*;
import com.museum.service.ItemInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ItemInfoServiceImpl implements ItemInfoService {
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
        ExhibitsInfoExample example =new ExhibitsInfoExample();
        List<ExhibitsInfo> list = exhibitsInfoMapper.selectByExample(example);
        List<ExhibitsInfoCustom> newList=new ArrayList<>();
        for (ExhibitsInfo exhibitsInfo:list)
        {
            ExhibitsInfoCustom custom=new ExhibitsInfoCustom();
            MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(exhibitsInfo.getOperatorId());
            BeanUtils.copyProperties(exhibitsInfo,custom);
            ExhibitsType exhibitsType = exhibitsTypeMapper.selectByPrimaryKey(exhibitsInfo.getTypeId());
            custom.setTypeName(exhibitsType.getTypeName());
            if(memberInfo!=null){
                memberInfo.setPassword(null);
            }
            custom.setMemberInfo(memberInfo);
            newList.add(custom);
        }
        PageInfo<ExhibitsInfo> pageInfo=new PageInfo<>(list);
        PageHelperResult result=new PageHelperResult();
        result.setRows(newList);
        result.setTotal((int) pageInfo.getTotal());
        return result;
    }
    //添加展品
    @Override
    public ExhibitsInfo insertItemInfo(ExhibitsInfo exhibitsInfo) {
        exhibitsInfo.setAddTime(new Date());
        int i = exhibitsInfoMapper.insertSelective(exhibitsInfo);
        return exhibitsInfo;
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
    public ExhibitsInfo getExhibitsInfoById(Integer id) {
        ExhibitsInfo exhibitsInfoResult = exhibitsInfoMapper.selectByPrimaryKey(id);

        return exhibitsInfoResult;
    }




}
