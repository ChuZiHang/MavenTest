package com.lol.common.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lol.common.model.ds1.ProductInfo;
import com.lol.common.model.ds1.ProductInfoExample;


public interface ProductInfoService {
	int countByExample(ProductInfoExample example);

	int deleteByExample(ProductInfoExample example);

	int insert(ProductInfo record);

	int insertSelective(ProductInfo record);

	List<ProductInfo> selectByExample(ProductInfoExample example);

	int updateByExampleSelective(@Param("record") ProductInfo record, @Param("example") ProductInfoExample example);

	int updateByExample(@Param("record") ProductInfo record, @Param("example") ProductInfoExample example);
	
	ProductInfo selectByPrimaryKey(long id);
	
	public int updateByPrimaryKeySelective(ProductInfo record);
	
	public int deleteByPrimaryKey(long id);
	
	
}
