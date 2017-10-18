package com.lol.common.mapper.merchant;

import com.lol.common.model.merchant.SaasMerchantInfo;
import com.lol.common.model.merchant.SaasMerchantInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SaasMerchantInfoMapper {
    int countByExample(SaasMerchantInfoExample example);

    int deleteByExample(SaasMerchantInfoExample example);

    int deleteByPrimaryKey(Integer mcId);

    int insert(SaasMerchantInfo record);

    int insertSelective(SaasMerchantInfo record);

    List<SaasMerchantInfo> selectByExample(SaasMerchantInfoExample example);

    SaasMerchantInfo selectByPrimaryKey(Integer mcId);

    int updateByExampleSelective(@Param("record") SaasMerchantInfo record, @Param("example") SaasMerchantInfoExample example);

    int updateByExample(@Param("record") SaasMerchantInfo record, @Param("example") SaasMerchantInfoExample example);

    int updateByPrimaryKeySelective(SaasMerchantInfo record);

    int updateByPrimaryKey(SaasMerchantInfo record);
}