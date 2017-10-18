package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.CouponList;
import com.lol.common.model.ds1.CouponListExample;
import com.lol.common.model.ds1.CouponListWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CouponListMapper {
    int countByExample(CouponListExample example);

    int deleteByExample(CouponListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CouponListWithBLOBs record);

    int insertSelective(CouponListWithBLOBs record);

    List<CouponListWithBLOBs> selectByExampleWithBLOBs(CouponListExample example);

    List<CouponList> selectByExample(CouponListExample example);

    CouponListWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CouponListWithBLOBs record, @Param("example") CouponListExample example);

    int updateByExampleWithBLOBs(@Param("record") CouponListWithBLOBs record, @Param("example") CouponListExample example);

    int updateByExample(@Param("record") CouponList record, @Param("example") CouponListExample example);

    int updateByPrimaryKeySelective(CouponListWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CouponListWithBLOBs record);

    int updateByPrimaryKey(CouponList record);
}