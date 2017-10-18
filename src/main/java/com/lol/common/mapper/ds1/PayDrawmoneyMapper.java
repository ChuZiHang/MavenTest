package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.PayDrawmoney;
import com.lol.common.model.ds1.PayDrawmoneyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayDrawmoneyMapper {
    int countByExample(PayDrawmoneyExample example);

    int deleteByExample(PayDrawmoneyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PayDrawmoney record);

    int insertSelective(PayDrawmoney record);

    List<PayDrawmoney> selectByExample(PayDrawmoneyExample example);

    PayDrawmoney selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PayDrawmoney record, @Param("example") PayDrawmoneyExample example);

    int updateByExample(@Param("record") PayDrawmoney record, @Param("example") PayDrawmoneyExample example);

    int updateByPrimaryKeySelective(PayDrawmoney record);

    int updateByPrimaryKey(PayDrawmoney record);
}