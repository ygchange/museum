package com.museum.service;

import com.museum.common.pojo.PageHelperResult;
import com.museum.pojo.SelectLog;

public interface SelectLogService {
    PageHelperResult getSelectLogList(Integer page, Integer rows,Long before,Long after);
    void insertSelectLog(SelectLog selectLog);
}
