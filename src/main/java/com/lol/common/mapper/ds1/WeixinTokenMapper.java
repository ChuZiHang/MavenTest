package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.WeixinToken;
import com.lol.common.model.ds1.WeixinTokenExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WeixinTokenMapper {
    int countByExample(WeixinTokenExample example);

    int deleteByExample(WeixinTokenExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WeixinToken record);

    int insertSelective(WeixinToken record);

    List<WeixinToken> selectByExample(WeixinTokenExample example);

    WeixinToken selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WeixinToken record, @Param("example") WeixinTokenExample example);

    int updateByExample(@Param("record") WeixinToken record, @Param("example") WeixinTokenExample example);

    int updateByPrimaryKeySelective(WeixinToken record);

    int updateByPrimaryKey(WeixinToken record);
}