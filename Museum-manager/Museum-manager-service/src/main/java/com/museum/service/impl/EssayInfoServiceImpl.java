package com.museum.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.museum.common.pojo.PageHelperResult;
import com.museum.mapper.EssayInfoMapper;
import com.museum.mapper.MemberInfoMapper;
import com.museum.pojo.EssayInfo;
import com.museum.pojo.EssayInfoExample;
import com.museum.pojo.MemberInfo;
import com.museum.service.EssayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EssayInfoServiceImpl implements EssayInfoService {
    @Autowired
    private EssayInfoMapper essayInfoMapper;
    @Autowired
    private MemberInfoMapper memberInfoMapper;
    //插入新的文章
    @Override
    public void insertEssayInfo(EssayInfo essayInfo) {

        essayInfoMapper.insertSelective(essayInfo);
    }
    //查询文章列表
    @Override
    public PageHelperResult getEssayInfo(Integer page, Integer rows, String bucketHostName) {
        PageHelper.startPage(page,rows);
        EssayInfoExample example=new EssayInfoExample();
        example.setOrderByClause("add_time DESC" );
        List<EssayInfo> essayInfos = essayInfoMapper.selectByExampleWithBLOBs(example);
        List<Map<String,Object>> newList=new ArrayList<>();
        for (EssayInfo essayInfo:essayInfos
             ) {
            essayInfo.setContent(essayInfo.getContent().replaceAll("src=\"","src=\""+bucketHostName));
            Map<String,Object> map=new HashMap<>();
            MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(essayInfo.getOperatorId());
            map.put("essayInfo",essayInfo);
            map.put("nickName",memberInfo.getNickname());
            map.put("telephone",memberInfo.getTelephone());
            newList.add(map);
        }
        PageInfo<EssayInfo> pageInfo=new PageInfo<>(essayInfos);
        PageHelperResult result=new PageHelperResult();
        result.setRows(newList);
        result.setTotal((int) pageInfo.getTotal());
        return result;
    }

    @Override
    public Integer updateEssayInfoById(EssayInfo essayInfo) {

        int i = essayInfoMapper.updateByPrimaryKeySelective(essayInfo);
        return i;
    }

    @Override
    public Integer deleteEssayInfoById(Integer id) {
        int i = essayInfoMapper.deleteByPrimaryKey(id);
        return i;
    }
    //查询文章
    @Override
    public List<EssayInfo> getLatestEssayInfo(Integer essayType) {
        EssayInfoExample example=new EssayInfoExample();
        EssayInfoExample.Criteria criteria = example.createCriteria();
        criteria.andEssayTypeEqualTo(essayType);
        criteria.andStatusEqualTo(1);
        example.setOrderByClause("add_time DESC" );
        List<EssayInfo> essayInfos = essayInfoMapper.selectByExample(example);

        return essayInfos;
    }

    @Override
    public List<EssayInfo> getHotEssayInfo(Integer essayType) {
        EssayInfoExample example=new EssayInfoExample();
        EssayInfoExample.Criteria criteria = example.createCriteria();
        criteria.andEssayTypeEqualTo(essayType);
        criteria.andStatusEqualTo(1);
        example.setOrderByClause("views DESC" );
        List<EssayInfo> essayInfos = essayInfoMapper.selectByExample(example);
        return essayInfos;
    }

    @Override
    public List<EssayInfo> getHotEssayInfo() {
        EssayInfoExample example=new EssayInfoExample();
        EssayInfoExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(1);
        example.setOrderByClause("views DESC" );
        List<EssayInfo> essayInfos = essayInfoMapper.selectByExample(example);
        return essayInfos;
    }

    @Override
    public List<EssayInfo> getDynamicOrCulture(Integer essayType) {
        EssayInfoExample example=new EssayInfoExample();
        EssayInfoExample.Criteria criteria = example.createCriteria();
        criteria.andEssayTypeEqualTo(essayType);
        criteria.andStatusEqualTo(1);
        example.setOrderByClause("add_time DESC");
        List<EssayInfo> list = essayInfoMapper.selectByExample(example);
        return list;
    }

    @Override
    public PageHelperResult getDynamicOrCultureByPage(Integer essayType, Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        EssayInfoExample example=new EssayInfoExample();
        EssayInfoExample.Criteria criteria = example.createCriteria();
        criteria.andEssayTypeEqualTo(essayType);
        criteria.andStatusEqualTo(1);
        example.setOrderByClause("add_time DESC");
        List<EssayInfo> list = essayInfoMapper.selectByExample(example);
        List<Map<String,Object>> newList=new ArrayList<>();
        for (EssayInfo essayInfo :list
                ) {
            Map<String,Object> map=new HashMap<>();
            map.put("addTime",essayInfo.getAddTime());
            map.put("title",essayInfo.getTitle());
            map.put("id",essayInfo.getId());
            newList.add(map);
        }
        PageInfo<EssayInfo> pageInfo=new PageInfo<>(list);
        PageHelperResult result=new PageHelperResult();
        result.setTotal((int) pageInfo.getTotal());
        result.setRows(newList);
        return result;
    }

    @Override
    public EssayInfo getDynamicOrCultureById(Integer id) {
        EssayInfo essayInfo = essayInfoMapper.selectByPrimaryKey(id);
        return essayInfo;
    }


}
