package com.lol.common.service;

import java.util.List;

import com.lol.common.model.ds0.InfoArea;
import com.lol.common.model.ds0.InfoAreaExample;

public interface InfoAreaService {

    List<InfoArea> selectByExample(InfoAreaExample example);
}
