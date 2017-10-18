package com.lol.common.service;

import java.util.List;

import com.lol.common.model.ds1.AppInfo;
import com.lol.common.model.ds1.AppInfoExample;

public interface AppInfoService {

    public List<AppInfo> selectByExample(AppInfoExample example);

    public AppInfo selectByPrimaryKey(int appId);
}
