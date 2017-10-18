package com.lol.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds1.CouponInfoMapper;
import com.lol.common.model.ds1.CouponInfo;
import com.lol.common.model.ds1.CouponInfoExample;
import com.lol.common.service.CouponInfoService;


@Service("couponInfoService")
public class CouponInfoServiceImpl implements CouponInfoService {

	@Resource
	CouponInfoMapper CouponInfoMapper;

	@Override
	public int countByExample(CouponInfoExample example) {
		return CouponInfoMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(CouponInfoExample example) {
		return CouponInfoMapper.deleteByExample(example);
	}

	@Override
	public int insert(CouponInfo record) {
		return CouponInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(CouponInfo record) {
		return CouponInfoMapper.insertSelective(record);
	}

	@Override
	public List<CouponInfo> selectByExample(CouponInfoExample example) {
		return CouponInfoMapper.selectByExample(example);
	}

	@Override
	public int updateByExampleSelective(CouponInfo record, CouponInfoExample example) {
		return CouponInfoMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(CouponInfo record, CouponInfoExample example) {
		return CouponInfoMapper.updateByExample(record, example);
	}

	@Override
	public CouponInfo selectByPrimaryKey(int id) {
	 return	CouponInfoMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(CouponInfo record) {
		return CouponInfoMapper.updateByPrimaryKeySelective(record);
	}
	
	public int deleteByPrimaryKey(int id) {
		return CouponInfoMapper.deleteByPrimaryKey(id);
	}
	
}
