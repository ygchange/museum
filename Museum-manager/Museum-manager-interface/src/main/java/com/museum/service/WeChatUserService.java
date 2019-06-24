package com.museum.service;

import com.museum.common.pojo.PageHelperResult;
import com.museum.pojo.WechatUser;

public interface WeChatUserService {
    PageHelperResult getWeChatList(Integer page,Integer rows);
    void insertWeChatInfo(WechatUser wechatUser);
    void updateStatusByUserId(WechatUser wechatUser);
    WechatUser selectWeChatInfoByUserId(WechatUser wechatUser);
}
