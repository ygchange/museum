package com.museum.service;

import com.museum.common.pojo.PageHelperResult;
import com.museum.pojo.LostInfo;

import java.util.List;

public interface LostService {
    PageHelperResult getLostList(Integer page, Integer rows);

    Integer deleteLostById(Integer id);

    void insertLost(LostInfo lostInfo);

    Integer updateLostStatus(LostInfo lostInfo);

    List<LostInfo> getWeChatLostList();
}
