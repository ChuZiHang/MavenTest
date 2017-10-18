package com.lol.common.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lol.common.model.merchant.ProductSms;
import com.lol.common.model.merchant.ProductSmsExample;

public interface ProductSmsService {

    int countByExample(ProductSmsExample example);

    int deleteByExample(ProductSmsExample example);

    int insert(ProductSms record);

    int insertSelective(ProductSms record);

    List<ProductSms> selectByExample(ProductSmsExample example);

    ProductSms selectByPrimaryKey(long id);
    
    int updateByExampleSelective(@Param("record") ProductSms record, @Param("example") ProductSmsExample example);

    int updateByExample(@Param("record") ProductSms record, @Param("example") ProductSmsExample example);

}
