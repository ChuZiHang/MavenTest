package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.DeptInfo;
import com.lol.common.model.ds1.DeptInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeptInfoMapper {
    int countByExample(DeptInfoExample example);

    int deleteByExample(DeptInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DeptInfo record);

    int insertSelective(DeptInfo record);

    List<DeptInfo> selectByExample(DeptInfoExample example);

    DeptInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DeptInfo record, @Param("example") DeptInfoExample example);

    int updateByExample(@Param("record") DeptInfo record, @Param("example") DeptInfoExample example);

    int updateByPrimaryKeySelective(DeptInfo record);

    int updateByPrimaryKey(DeptInfo record);
}