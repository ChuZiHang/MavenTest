package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.CouponInfo;
import com.lol.common.model.ds1.CouponInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CouponInfoMapper {
    int countByExample(CouponInfoExample example);

    int deleteByExample(CouponInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CouponInfo record);

    int insertSelective(CouponInfo record);

    List<CouponInfo> selectByExample(CouponInfoExample example);

    CouponInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CouponInfo record, @Param("example") CouponInfoExample example);

    int updateByExample(@Param("record") CouponInfo record, @Param("example") CouponInfoExample example);

    int updateByPrimaryKeySelective(CouponInfo record);

    int updateByPrimaryKey(CouponInfo record);
}