package com.museum.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.museum.common.pojo.PageHelperResult;
import com.museum.common.utils.UnicodeUtil;
import com.museum.custom.SelectLogCustom;
import com.museum.mapper.ExhibitsInfoMapper;
import com.museum.mapper.SelectLogMapper;
import com.museum.mapper.WechatUserMapper;
import com.museum.pojo.ExhibitsInfo;
import com.museum.pojo.SelectLog;
import com.museum.pojo.SelectLogExample;
import com.museum.pojo.WechatUser;
import com.museum.service.SelectLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SelectLogServiceImpl implements SelectLogService {
    @Autowired
    private SelectLogMapper selectLogMapper;
    @Autowired
    private ExhibitsInfoMapper exhibitsInfoMapper;
    @Autowired
    private WechatUserMapper wechatUserMapper;
    //分页查询数据统计
    @Override
    public PageHelperResult getSelectLogList(Integer page, Integer rows,Long before,Long after) {
//        Long currentTimestamps=System.currentTimeMillis();
//        Long oneDayTimestamps= Long.valueOf(60*60*24*1000);
//        long before = currentTimestamps -(currentTimestamps + 60 * 60 * 8 * 1000) % oneDayTimestamps;
//        long after=before+60*60*24*1000;


        PageHelper.startPage(page,rows);

        SelectLogExample example=new SelectLogExample();

        SelectLogExample.Criteria criteria = example.createCriteria();
        if(before!=null&&after!=null){
            Date beforeDate =new Date(before);
            Date afterDate =new Date(after);
            criteria.andSelectTimeBetween(beforeDate,afterDate);
        }

        List<SelectLog> selectLogs = selectLogMapper.selectByExample(example);

        List<SelectLogCustom> newList=new ArrayList<>();

        for (SelectLog selectLog :
                selectLogs) {
            SelectLogCustom custom =new SelectLogCustom();
            custom.setSelectTime(selectLog.getSelectTime());
            custom.setSelectUserIp(selectLog.getSelectUserIp());
            ExhibitsInfo exhibitsInfo = exhibitsInfoMapper.selectByPrimaryKey(selectLog.getExhibitsInfoId());
            if(exhibitsInfo==null){
                exhibitsInfo=new ExhibitsInfo();
                exhibitsInfo.setName("展品已被删除");
            }
            WechatUser wechatUser = wechatUserMapper.selectByPrimaryKey(selectLog.getWechatUserId());
            wechatUser.setNickName(UnicodeUtil.unicodeDecode(wechatUser.getNickName()));
            custom.setExhibitsInfo(exhibitsInfo);
            custom.setWechatUser(wechatUser);
            newList.add(custom);
        }
        PageInfo<SelectLog> pageInfo=new PageInfo<>(selectLogs);

        PageHelperResult result=new PageHelperResult();

        result.setRows(newList);

        result.setTotal((int) pageInfo.getTotal());

        return result;
    }
    //添加日志
    @Override
    public void insertSelectLog(SelectLog selectLog) {
        selectLogMapper.insertSelective(selectLog);
    }
}
