package com.museum.custom;

import com.museum.pojo.ExhibitsInfo;
import com.museum.pojo.SelectLog;
import com.museum.pojo.WechatUser;

public class SelectLogCustom extends SelectLog {
    private WechatUser wechatUser;
    private ExhibitsInfo exhibitsInfo;

    public WechatUser getWechatUser() {
        return wechatUser;
    }

    public void setWechatUser(WechatUser wechatUser) {
        this.wechatUser = wechatUser;
    }

    public ExhibitsInfo getExhibitsInfo() {
        return exhibitsInfo;
    }

    public void setExhibitsInfo(ExhibitsInfo exhibitsInfo) {
        this.exhibitsInfo = exhibitsInfo;
    }
}
