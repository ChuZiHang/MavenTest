package com.lol.common.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lol.common.model.merchant.SaasMerchantInfo;
import com.lol.common.model.merchant.SaasMerchantInfoExample;

public interface SaasMerchantInfoService {
	

    int countByExample(SaasMerchantInfoExample example);

    int deleteByExample(SaasMerchantInfoExample example);

    int insert(SaasMerchantInfo record);

    int insertSelective(SaasMerchantInfo record);

    List<SaasMerchantInfo> selectByExample(SaasMerchantInfoExample example);

    int updateByExampleSelective(@Param("record") SaasMerchantInfo record, @Param("example") SaasMerchantInfoExample example);

    int updateByExample(@Param("record") SaasMerchantInfo record, @Param("example") SaasMerchantInfoExample example);

    SaasMerchantInfo selectByPrimaryKey(int id);

	
}
