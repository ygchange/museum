package com.museum.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.museum.common.pojo.PageHelperResult;
import com.museum.mapper.LostInfoMapper;
import com.museum.pojo.LostInfo;
import com.museum.pojo.LostInfoExample;
import com.museum.service.LostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LostServiceImpl implements LostService {
    @Autowired
    private LostInfoMapper lostInfoMapper;
    @Override
    public PageHelperResult getLostList(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        LostInfoExample example=new LostInfoExample();
        List<LostInfo> list =lostInfoMapper.selectByExample(example);
        PageInfo<LostInfo> pageInfo=new PageInfo<>(list);
        PageHelperResult result=new PageHelperResult();
        result.setRows(list);
        result.setTotal((int) pageInfo.getTotal());
        return result;

    }

    @Override
    public Integer deleteLostById(Integer id) {
        int i = lostInfoMapper.deleteByPrimaryKey(id);
        return i;
    }

    @Override
    public void insertLost(LostInfo lostInfo) {
        Date date =new Date();
        lostInfoMapper.insertSelective(lostInfo);
    }

    @Override
    public Integer updateLostStatus(LostInfo lostInfo) {
        int i = lostInfoMapper.updateByPrimaryKeySelective(lostInfo);
        return i;
    }

    @Override
    public List<LostInfo> getWeChatLostList() {
        LostInfoExample example=new LostInfoExample();
        example.setOrderByClause("id desc");
        LostInfoExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(0);
        List<LostInfo> lostInfos = lostInfoMapper.selectByExample(example);
        return lostInfos;
    }
}
