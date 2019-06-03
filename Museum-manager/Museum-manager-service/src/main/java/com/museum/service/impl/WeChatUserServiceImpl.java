package com.museum.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.museum.common.pojo.PageHelperResult;
import com.museum.common.utils.UnicodeUtil;
import com.museum.mapper.WechatUserMapper;
import com.museum.pojo.WechatUser;
import com.museum.pojo.WechatUserExample;
import com.museum.service.WeChatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class WeChatUserServiceImpl implements WeChatUserService {
    @Autowired
    private WechatUserMapper wechatUserMapper;
    //分页查询用户信息
    @Override
    public PageHelperResult getWeChatList(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        WechatUserExample example=new WechatUserExample();
        List<WechatUser> list = wechatUserMapper.selectByExample(example);
        for (WechatUser wechatUser :list ) {
            String string=wechatUser.getNickName();
            string = UnicodeUtil.unicodeDecode(string);
            wechatUser.setNickName(string);
        }
        PageInfo<WechatUser> pageInfo=new PageInfo<>(list);
        PageHelperResult result=new PageHelperResult();
        result.setRows(list);
        result.setTotal((int) pageInfo.getTotal());
        return result;
    }
}
