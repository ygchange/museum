package com.museum.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.museum.common.pojo.PageHelperResult;
import com.museum.custom.LostInfoCustom;
import com.museum.mapper.LostInfoMapper;
import com.museum.mapper.MemberInfoMapper;
import com.museum.pojo.LostInfo;
import com.museum.pojo.LostInfoExample;
import com.museum.pojo.MemberInfo;
import com.museum.service.LostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LostServiceImpl implements LostService {
    @Autowired
    private LostInfoMapper lostInfoMapper;
    @Autowired
    private MemberInfoMapper memberInfoMapper;
    @Override
    public PageHelperResult getLostList(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        LostInfoExample example=new LostInfoExample();
        example.setOrderByClause("status ASC,id DESC");
        List<LostInfo> list =lostInfoMapper.selectByExample(example);
        List<LostInfoCustom> newList=new ArrayList<>();
        for (LostInfo lostInfo :
                list) {
            LostInfoCustom custom=new LostInfoCustom();
            BeanUtils.copyProperties(lostInfo,custom);
            MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(lostInfo.getOperatorId());
            if(memberInfo!=null){
                memberInfo.setPassword(null);
            }
            custom.setMemberInfo(memberInfo);
            newList.add(custom);
        }
        PageInfo<LostInfo> pageInfo=new PageInfo<>(list);
        PageHelperResult result=new PageHelperResult();
        result.setRows(newList);
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
        example.setOrderByClause("id DESC");
        LostInfoExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(0);
        List<LostInfo> lostInfos = lostInfoMapper.selectByExample(example);
        System.out.println(lostInfos);
        return lostInfos;
    }
}
