package com.museum.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.museum.common.pojo.PageHelperResult;
import com.museum.mapper.AnnouncementInfoMapper;
import com.museum.pojo.AnnouncementInfo;
import com.museum.pojo.AnnouncementInfoExample;
import com.museum.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class AnnouncementServiceImpl  implements AnnouncementService {
    @Autowired
    private AnnouncementInfoMapper announcementInfoMapper;
    @Override
    //查询公告
    public List<AnnouncementInfo> getWeChatAnnouncementList() {
        AnnouncementInfoExample example=new AnnouncementInfoExample();
        example.setOrderByClause("type DESC,add_time DESC");
        AnnouncementInfoExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(1);
        List<AnnouncementInfo> list = announcementInfoMapper.selectByExample(example);
        return list;
    }

    @Override
    //根据id查询公告
    public AnnouncementInfo getWeChatAnnouncementById(Integer id) {
        AnnouncementInfo announcementInfo = announcementInfoMapper.selectByPrimaryKey(id);
        return announcementInfo;
    }

    @Override
    //分页查询公告
    public PageHelperResult getAnnouncementList(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        AnnouncementInfoExample example=new AnnouncementInfoExample();
        example.setOrderByClause("type DESC,add_time DESC");
        List<AnnouncementInfo> list = announcementInfoMapper.selectByExample(example);
        PageInfo<AnnouncementInfo> pageInfo=new PageInfo<>(list);
        PageHelperResult result=new PageHelperResult();
        result.setTotal((int) pageInfo.getTotal());
        result.setRows(list);
        return result;
    }

    @Override
    //添加公告
    public void insertAnnouncement(AnnouncementInfo announcementInfo) {
        announcementInfo.setAddTime(new Date());
        announcementInfoMapper.insertSelective(announcementInfo);
    }

    @Override
    //删除公告
    public Integer deleteAnnouncementById(Integer id) {
        int i = announcementInfoMapper.deleteByPrimaryKey(id);
        return i;
    }

    @Override
    public Integer updateAnnouncementById(AnnouncementInfo announcementInfo) {
        announcementInfo.setAddTime(new Date());
        int i = announcementInfoMapper.updateByPrimaryKeySelective(announcementInfo);
        return i;
    }
}
