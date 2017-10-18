package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.CodeInfo;
import com.lol.common.model.ds1.CodeInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CodeInfoMapper {
    int countByExample(CodeInfoExample example);

    int deleteByExample(CodeInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CodeInfo record);

    int insertSelective(CodeInfo record);

    List<CodeInfo> selectByExample(CodeInfoExample example);

    CodeInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CodeInfo record, @Param("example") CodeInfoExample example);

    int updateByExample(@Param("record") CodeInfo record, @Param("example") CodeInfoExample example);

    int updateByPrimaryKeySelective(CodeInfo record);

    int updateByPrimaryKey(CodeInfo record);
}