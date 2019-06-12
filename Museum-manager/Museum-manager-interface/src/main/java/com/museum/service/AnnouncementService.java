package com.museum.service;

import com.museum.common.pojo.PageHelperResult;
import com.museum.pojo.AnnouncementInfo;

import java.util.List;

public interface AnnouncementService {
    List<AnnouncementInfo> getWeChatAnnouncementList();

    AnnouncementInfo getWeChatAnnouncementById(Integer id);

    PageHelperResult getAnnouncementList(Integer page, Integer rows);

    void insertAnnouncement(AnnouncementInfo announcementInfo);

    Integer deleteAnnouncementById(Integer id);

    Integer updateAnnouncementById(AnnouncementInfo announcementInfo);
}
