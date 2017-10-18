package com.lol.common.service;

import java.util.List;

import com.lol.common.model.ds0.InfoAreaEx;
import com.lol.common.model.ds0.InfoAreaExExample;

public interface InfoAreaExService {

    List<InfoAreaEx> selectByExample(InfoAreaExExample example);
    
    
    InfoAreaEx selectByPrimaryKey(Double areaId);
}
