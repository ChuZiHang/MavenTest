package com.lol.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lol.common.bean.CurrentUserInfo;
import com.lol.common.mapper.ds1.CustInfoMapper;
import com.lol.common.mapper.ds1.UserLoginMapper;
import com.lol.common.model.ds1.CustInfo;
import com.lol.common.model.ds1.CustInfoExample;
import com.lol.common.model.ds1.UserLogin;
import com.lol.common.model.ds1.UserLoginExample;
import com.lol.common.service.UserLoginService;

@Service("userLoginService")
public class UserLoginServiceImpl implements UserLoginService {

	@Resource
	UserLoginMapper userLoginMapper;
	
	@Resource
	CustInfoMapper custInfoMapper;

	@Override
	public int countByExample(UserLoginExample example) {
		return userLoginMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(UserLoginExample example) {
		return userLoginMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return userLoginMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserLogin record) {
		return userLoginMapper.insert(record);
	}

	@Override
	public int insertSelective(UserLogin record) {
		return userLoginMapper.insertSelective(record);
	}

	@Override
	public List<UserLogin> selectByExample(UserLoginExample example) {
		return userLoginMapper.selectByExample(example);
	}

	@Override
	public UserLogin selectByPrimaryKey(Long id) {
		return userLoginMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(UserLogin record, UserLoginExample example) {
		return userLoginMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(UserLogin record, UserLoginExample example) {
		return userLoginMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(UserLogin record) {
		return userLoginMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserLogin record) {
		return userLoginMapper.updateByPrimaryKey(record);
	}

	@RequiresRoles("test")
	@Transactional(value = "tmDs1", rollbackFor = Exception.class)
	@Override
	public void testTm() {
		UserLogin userLogin = new UserLogin();
		userLogin.setUsername("user1");
		userLogin.setPassword("xxxx");
		insertSelective(userLogin);
		//System.out.println(1/0);//异常
		UserLoginExample example = new UserLoginExample();
		example.createCriteria().andUsernameEqualTo("user1");
		UserLogin userLogin2 = new UserLogin();
		userLogin2.setUsername("user2");
		updateByExampleSelective(userLogin2, example);
	}

	@Override
	public void lock(String userName, long time) {
		UserLogin userLogin = new UserLogin();
		userLogin.setIsLock(1);
		UserLoginExample example = new UserLoginExample();
		example.createCriteria().andUsernameEqualTo(userName);
		userLoginMapper.updateByExampleSelective(userLogin, example);
		
	}

	/**
	 * 获取当前用户信息
	 * 
	 * @param userName
	 * @return
	 */
	@Override
	public CurrentUserInfo getCurrentUserInfo() {
		CurrentUserInfo currentUserInfo = new CurrentUserInfo();
		Object principal = SecurityUtils.getSubject().getPrincipal();
		if(principal ==null ){
			return null;
		}
		//获取用户信息
		UserLoginExample userLoginExample = new UserLoginExample();
		userLoginExample.createCriteria().andStatusEqualTo(0).andUsernameEqualTo(principal.toString());
		List<UserLogin> userLogins = userLoginMapper.selectByExample(userLoginExample);
		if(userLogins!=null && userLogins.size()>0){
			UserLogin userLogin = userLogins.get(0);
			currentUserInfo.setUserLogin(userLogin);
			//获取所属商户信息
			CustInfoExample custInfoExample = new CustInfoExample();
			custInfoExample.createCriteria().andStatusEqualTo(0).andSystemIdEqualTo(userLogin.getCustId());
			List<CustInfo> custInfos = custInfoMapper.selectByExample(custInfoExample);
			if(custInfos!=null && custInfos.size()>0){
				currentUserInfo.setCustInfo(custInfos.get(0));
			}
		}
		return currentUserInfo;
	}

	@Override
	public List<UserLogin> getUserLoginByCustId(int custId) {
		UserLoginExample userLoginExample = new UserLoginExample();
		userLoginExample.createCriteria().andStatusEqualTo(0).andCustIdEqualTo(custId);
		userLoginExample.setOrderByClause("id asc");
		List<UserLogin> userLogins = userLoginMapper.selectByExample(userLoginExample);
		return userLogins;
	}

}
