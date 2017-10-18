package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.MemberPwd;
import com.lol.common.model.ds1.MemberPwdExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberPwdMapper {
    int countByExample(MemberPwdExample example);

    int deleteByExample(MemberPwdExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberPwd record);

    int insertSelective(MemberPwd record);

    List<MemberPwd> selectByExample(MemberPwdExample example);

    MemberPwd selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberPwd record, @Param("example") MemberPwdExample example);

    int updateByExample(@Param("record") MemberPwd record, @Param("example") MemberPwdExample example);

    int updateByPrimaryKeySelective(MemberPwd record);

    int updateByPrimaryKey(MemberPwd record);
}