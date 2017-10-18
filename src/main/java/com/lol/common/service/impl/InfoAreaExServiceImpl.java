package com.lol.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds0.InfoAreaExMapper;
import com.lol.common.model.ds0.InfoAreaEx;
import com.lol.common.model.ds0.InfoAreaExExample;
import com.lol.common.service.InfoAreaExService;

@Service("infoExAreaService")
public class InfoAreaExServiceImpl implements InfoAreaExService {

    @Resource
    InfoAreaExMapper infoAreaExMapper;

    @Override
    public List<InfoAreaEx> selectByExample(InfoAreaExExample example) {
        return infoAreaExMapper.selectByExample(example);
    }
    
    public InfoAreaEx selectByPrimaryKey(Double areaId){
        return infoAreaExMapper.selectByPrimaryKey(areaId);
    }

}
