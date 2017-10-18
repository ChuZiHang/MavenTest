package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.PayBalance;
import com.lol.common.model.ds1.PayBalanceExample;
import com.lol.common.model.ds1.PayBalanceWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayBalanceMapper {
    int countByExample(PayBalanceExample example);

    int deleteByExample(PayBalanceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PayBalanceWithBLOBs record);

    int insertSelective(PayBalanceWithBLOBs record);

    List<PayBalanceWithBLOBs> selectByExampleWithBLOBs(PayBalanceExample example);

    List<PayBalance> selectByExample(PayBalanceExample example);

    PayBalanceWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PayBalanceWithBLOBs record, @Param("example") PayBalanceExample example);

    int updateByExampleWithBLOBs(@Param("record") PayBalanceWithBLOBs record, @Param("example") PayBalanceExample example);

    int updateByExample(@Param("record") PayBalance record, @Param("example") PayBalanceExample example);

    int updateByPrimaryKeySelective(PayBalanceWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(PayBalanceWithBLOBs record);

    int updateByPrimaryKey(PayBalance record);
}