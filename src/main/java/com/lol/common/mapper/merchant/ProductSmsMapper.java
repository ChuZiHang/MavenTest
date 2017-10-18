package com.lol.common.mapper.merchant;

import com.lol.common.model.merchant.ProductSms;
import com.lol.common.model.merchant.ProductSmsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductSmsMapper {
    int countByExample(ProductSmsExample example);

    int deleteByExample(ProductSmsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProductSms record);

    int insertSelective(ProductSms record);

    List<ProductSms> selectByExample(ProductSmsExample example);

    ProductSms selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProductSms record, @Param("example") ProductSmsExample example);

    int updateByExample(@Param("record") ProductSms record, @Param("example") ProductSmsExample example);

    int updateByPrimaryKeySelective(ProductSms record);

    int updateByPrimaryKey(ProductSms record);
}