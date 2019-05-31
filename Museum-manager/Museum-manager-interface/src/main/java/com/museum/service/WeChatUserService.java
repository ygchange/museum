package com.museum.service;

import com.museum.common.pojo.PageHelperResult;

public interface WeChatUserService {
    PageHelperResult getWeChatList(Integer page,Integer rows);
}
