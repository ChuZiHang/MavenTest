package com.lol.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lol.common.mapper.merchant.ProductSmsMapper;
import com.lol.common.model.merchant.ProductSms;
import com.lol.common.model.merchant.ProductSmsExample;
import com.lol.common.service.ProductSmsService;

@Service("productSmsService")
public class ProductSmsServiceImpl implements ProductSmsService {

    @Resource
    ProductSmsMapper productSmsMapper;

    @Override
    public int countByExample(ProductSmsExample example) {
        return productSmsMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(ProductSmsExample example) {
        return productSmsMapper.deleteByExample(example);
    }

    @Override
    public int insert(ProductSms record) {
        return productSmsMapper.insert(record);
    }

    @Override
    public int insertSelective(ProductSms record) {
        return productSmsMapper.insertSelective(record);
    }

    @Override
    public List<ProductSms> selectByExample(ProductSmsExample example) {
        return productSmsMapper.selectByExample(example);
    }

    @Override
    public int updateByExampleSelective(ProductSms record, ProductSmsExample example) {
        return productSmsMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(ProductSms record, ProductSmsExample example) {
        return productSmsMapper.updateByExample(record, example);
    }

    @Override
    public ProductSms selectByPrimaryKey(long id) {
        return productSmsMapper.selectByPrimaryKey(id);
    }

}
