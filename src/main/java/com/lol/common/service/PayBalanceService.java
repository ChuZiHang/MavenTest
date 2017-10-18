package com.lol.common.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lol.common.model.ds1.PayBalance;
import com.lol.common.model.ds1.PayBalanceExample;
import com.lol.common.model.ds1.PayBalanceWithBLOBs;

public interface PayBalanceService {

    int insertSelective(PayBalanceWithBLOBs record);

    List<PayBalanceWithBLOBs> selectByExampleWithBLOBs(PayBalanceExample example);

    List<PayBalance> selectByExample(PayBalanceExample example);

    PayBalanceWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PayBalanceWithBLOBs record, @Param("example") PayBalanceExample example);

    int updateByPrimaryKeySelective(PayBalanceWithBLOBs record);

}
