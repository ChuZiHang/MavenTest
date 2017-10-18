package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.ProductInfo;
import com.lol.common.model.ds1.ProductInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductInfoMapper {
    int countByExample(ProductInfoExample example);

    int deleteByExample(ProductInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProductInfo record);

    int insertSelective(ProductInfo record);

    List<ProductInfo> selectByExampleWithBLOBs(ProductInfoExample example);

    List<ProductInfo> selectByExample(ProductInfoExample example);

    ProductInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProductInfo record, @Param("example") ProductInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") ProductInfo record, @Param("example") ProductInfoExample example);

    int updateByExample(@Param("record") ProductInfo record, @Param("example") ProductInfoExample example);

    int updateByPrimaryKeySelective(ProductInfo record);

    int updateByPrimaryKeyWithBLOBs(ProductInfo record);

    int updateByPrimaryKey(ProductInfo record);
}