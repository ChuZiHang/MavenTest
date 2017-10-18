package com.lol.common.mapper.ds0;

import com.lol.common.model.ds0.SysAuthRule;
import com.lol.common.model.ds0.SysAuthRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysAuthRuleMapper {
    int countByExample(SysAuthRuleExample example);

    int deleteByExample(SysAuthRuleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysAuthRule record);

    int insertSelective(SysAuthRule record);

    List<SysAuthRule> selectByExample(SysAuthRuleExample example);

    SysAuthRule selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysAuthRule record, @Param("example") SysAuthRuleExample example);

    int updateByExample(@Param("record") SysAuthRule record, @Param("example") SysAuthRuleExample example);

    int updateByPrimaryKeySelective(SysAuthRule record);

    int updateByPrimaryKey(SysAuthRule record);
}