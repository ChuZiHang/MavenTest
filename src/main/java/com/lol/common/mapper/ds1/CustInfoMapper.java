package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.CustInfo;
import com.lol.common.model.ds1.CustInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustInfoMapper {
    int countByExample(CustInfoExample example);

    int deleteByExample(CustInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CustInfo record);

    int insertSelective(CustInfo record);

    List<CustInfo> selectByExampleWithBLOBs(CustInfoExample example);

    List<CustInfo> selectByExample(CustInfoExample example);

    CustInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CustInfo record, @Param("example") CustInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") CustInfo record, @Param("example") CustInfoExample example);

    int updateByExample(@Param("record") CustInfo record, @Param("example") CustInfoExample example);

    int updateByPrimaryKeySelective(CustInfo record);

    int updateByPrimaryKeyWithBLOBs(CustInfo record);

    int updateByPrimaryKey(CustInfo record);
}