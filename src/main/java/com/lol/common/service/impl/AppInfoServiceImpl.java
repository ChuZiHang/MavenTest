package com.lol.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds1.AppInfoMapper;
import com.lol.common.model.ds1.AppInfo;
import com.lol.common.model.ds1.AppInfoExample;
import com.lol.common.service.AppInfoService;

@Service("appInfoService")
public class AppInfoServiceImpl implements AppInfoService {

    @Resource
    private AppInfoMapper appInfoMapper;

    @Override
    public List<AppInfo> selectByExample(AppInfoExample example) {
        return appInfoMapper.selectByExample(example);
    }

    @Override
    public AppInfo selectByPrimaryKey(int appId) {
        return appInfoMapper.selectByPrimaryKey(appId);
    }

}
