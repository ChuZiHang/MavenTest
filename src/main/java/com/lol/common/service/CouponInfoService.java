package com.lol.common.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lol.common.model.ds1.CouponInfo;
import com.lol.common.model.ds1.CouponInfoExample;

public interface CouponInfoService {

    int countByExample(CouponInfoExample example);

    int deleteByExample(CouponInfoExample example);

    int insert(CouponInfo record);

    int insertSelective(CouponInfo record);

    List<CouponInfo> selectByExample(CouponInfoExample example);

    int updateByExampleSelective(@Param("record") CouponInfo record, @Param("example") CouponInfoExample example);

    int updateByExample(@Param("record") CouponInfo record, @Param("example") CouponInfoExample example);

    CouponInfo selectByPrimaryKey(int id);

    public int updateByPrimaryKeySelective(CouponInfo record);

    public int deleteByPrimaryKey(int id);

}
