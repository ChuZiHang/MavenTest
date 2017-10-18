package com.lol.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lol.common.mapper.ds1.CouponListMapper;
import com.lol.common.model.ds1.CouponList;
import com.lol.common.model.ds1.CouponListExample;
import com.lol.common.model.ds1.CouponListWithBLOBs;
import com.lol.common.service.CouponListService;


@Service("couponListService")
public class CouponListServiceImpl implements CouponListService {

	@Resource
	CouponListMapper CouponListMapper;

	@Override
	public int countByExample(CouponListExample example) {
		return CouponListMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(CouponListExample example) {
		return CouponListMapper.deleteByExample(example);
	}

	@Override
	public int insert(CouponListWithBLOBs record) {
		return CouponListMapper.insert(record);
	}

	@Override
	public int insertSelective(CouponListWithBLOBs record) {
		return CouponListMapper.insertSelective(record);
	}

	@Override
	public List<CouponList> selectByExample(CouponListExample example) {
		return CouponListMapper.selectByExample(example);
	}

	@Override
	public int updateByExampleSelective(CouponListWithBLOBs record, CouponListExample example) {
		return CouponListMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(CouponListWithBLOBs record, CouponListExample example) {
		return CouponListMapper.updateByExample(record, example);
	}

	@Override
	public CouponList selectByPrimaryKey(int id) {
	 return	CouponListMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(CouponListWithBLOBs record) {
		return CouponListMapper.updateByPrimaryKeySelective(record);
	}
	
	public int deleteByPrimaryKey(int id) {
		return CouponListMapper.deleteByPrimaryKey(id);
	}

    @Override
    public List<CouponListWithBLOBs> selectByExampleWithBLOBs(CouponListExample example) {
        return CouponListMapper.selectByExampleWithBLOBs(example);
    }
	
}
