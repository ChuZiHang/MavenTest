package com.lol.common.service;

import com.lol.common.model.ds1.*;

import java.util.List;
import java.util.Map;

public interface MemberPwdService {

    List<MemberPwd> selectByExample(MemberPwdExample example);

    MemberPwd selectByPrimaryKey(int id);

    int insert(MemberPwd codeInfo);

    int count(MemberPwdExample example);

    int updateByPrimaryKeySelective(MemberPwd record);

    int updateByExample(MemberPwd record, MemberPwdExample example);

}
