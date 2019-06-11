package com.museum.service.impl;

import com.museum.mapper.AnnouncementInfoMapper;
import com.museum.pojo.AnnouncementInfo;
import com.museum.pojo.AnnouncementInfoExample;
import com.museum.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<AnnouncementInfo> list = announcementInfoMapper.selectByExample(example);
        return list;
    }
}
