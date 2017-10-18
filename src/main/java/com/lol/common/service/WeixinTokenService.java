package com.lol.common.service;

import java.util.List;
import java.util.Map;

import com.lol.common.model.ds1.WeixinToken;
import com.lol.common.model.ds1.WeixinTokenExample;

public interface WeixinTokenService {

    List<WeixinToken> selectWeixinTokensByExample(WeixinTokenExample example);

    List<Map<String, Object>> selectWeixinTokensBySql(String sql, Object[] args);

    public int updateByPrimaryKeySelective(WeixinToken record);

    public int countByExample(WeixinTokenExample example);

    public int insert(WeixinToken record);

    public int insertSelective(WeixinToken record);

    public WeixinToken selectByPrimaryKey(int id);

}
