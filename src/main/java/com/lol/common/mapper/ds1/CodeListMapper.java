package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.CodeList;
import com.lol.common.model.ds1.CodeListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CodeListMapper {
    int countByExample(CodeListExample example);

    int deleteByExample(CodeListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CodeList record);

    int insertSelective(CodeList record);

    List<CodeList> selectByExample(CodeListExample example);

    CodeList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CodeList record, @Param("example") CodeListExample example);

    int updateByExample(@Param("record") CodeList record, @Param("example") CodeListExample example);

    int updateByPrimaryKeySelective(CodeList record);

    int updateByPrimaryKey(CodeList record);
}