package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.AppInfo;
import com.lol.common.model.ds1.AppInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppInfoMapper {
    int countByExample(AppInfoExample example);

    int deleteByExample(AppInfoExample example);

    int deleteByPrimaryKey(Integer appId);

    int insert(AppInfo record);

    int insertSelective(AppInfo record);

    List<AppInfo> selectByExample(AppInfoExample example);

    AppInfo selectByPrimaryKey(Integer appId);

    int updateByExampleSelective(@Param("record") AppInfo record, @Param("example") AppInfoExample example);

    int updateByExample(@Param("record") AppInfo record, @Param("example") AppInfoExample example);

    int updateByPrimaryKeySelective(AppInfo record);

    int updateByPrimaryKey(AppInfo record);
}