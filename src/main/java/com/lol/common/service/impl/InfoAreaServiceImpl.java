package com.lol.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds0.InfoAreaMapper;
import com.lol.common.model.ds0.InfoArea;
import com.lol.common.model.ds0.InfoAreaExample;
import com.lol.common.service.InfoAreaService;

@Service("infoAreaService")
public class InfoAreaServiceImpl implements InfoAreaService {

    @Resource
    InfoAreaMapper infoAreaMapper;

    @Override
    public List<InfoArea> selectByExample(InfoAreaExample example) {
        return infoAreaMapper.selectByExample(example);
    }
}
