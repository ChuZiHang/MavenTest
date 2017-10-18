package com.lol.common.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lol.common.model.ds1.CouponList;
import com.lol.common.model.ds1.CouponListExample;
import com.lol.common.model.ds1.CouponListWithBLOBs;

public interface CouponListService {

    int countByExample(CouponListExample example);

    int deleteByExample(CouponListExample example);

    int insert(CouponListWithBLOBs record);

    int insertSelective(CouponListWithBLOBs record);

    List<CouponList> selectByExample(CouponListExample example);
    
    List<CouponListWithBLOBs> selectByExampleWithBLOBs(CouponListExample example);

    int updateByExampleSelective(@Param("record") CouponListWithBLOBs record, @Param("example") CouponListExample example);

    int updateByExample(@Param("record") CouponListWithBLOBs record, @Param("example") CouponListExample example);

    CouponList selectByPrimaryKey(int id);

    public int updateByPrimaryKeySelective(CouponListWithBLOBs record);

    public int deleteByPrimaryKey(int id);

}
