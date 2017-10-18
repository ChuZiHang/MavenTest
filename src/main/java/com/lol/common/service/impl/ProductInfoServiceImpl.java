package com.lol.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds1.ProductInfoMapper;
import com.lol.common.model.ds1.ProductInfo;
import com.lol.common.model.ds1.ProductInfoExample;
import com.lol.common.service.ProductInfoService;


@Service("productInfoService")
public class ProductInfoServiceImpl implements ProductInfoService {

	@Resource
	ProductInfoMapper productInfoMapper;

	@Override
	public int countByExample(ProductInfoExample example) {
		return productInfoMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(ProductInfoExample example) {
		return productInfoMapper.deleteByExample(example);
	}

	@Override
	public int insert(ProductInfo record) {
		return productInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(ProductInfo record) {
		return productInfoMapper.insertSelective(record);
	}

	@Override
	public List<ProductInfo> selectByExample(ProductInfoExample example) {
		return productInfoMapper.selectByExample(example);
	}

	@Override
	public int updateByExampleSelective(ProductInfo record, ProductInfoExample example) {
		return productInfoMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(ProductInfo record, ProductInfoExample example) {
		return productInfoMapper.updateByExample(record, example);
	}

	@Override
	public ProductInfo selectByPrimaryKey(long id) {
	 return	productInfoMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(ProductInfo record) {
		return productInfoMapper.updateByPrimaryKeySelective(record);
	}
	
	public int deleteByPrimaryKey(long id) {
		return productInfoMapper.deleteByPrimaryKey(id);
	}
	
}
