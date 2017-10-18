package com.lol.common.mapper.ds1;

import com.lol.common.model.ds1.CommentInfo;
import com.lol.common.model.ds1.CommentInfoExample;
import com.lol.common.model.ds1.CommentInfoWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommentInfoMapper {
    int countByExample(CommentInfoExample example);

    int deleteByExample(CommentInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CommentInfoWithBLOBs record);

    int insertSelective(CommentInfoWithBLOBs record);

    List<CommentInfoWithBLOBs> selectByExampleWithBLOBs(CommentInfoExample example);

    List<CommentInfo> selectByExample(CommentInfoExample example);

    CommentInfoWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CommentInfoWithBLOBs record, @Param("example") CommentInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") CommentInfoWithBLOBs record, @Param("example") CommentInfoExample example);

    int updateByExample(@Param("record") CommentInfo record, @Param("example") CommentInfoExample example);

    int updateByPrimaryKeySelective(CommentInfoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CommentInfoWithBLOBs record);

    int updateByPrimaryKey(CommentInfo record);
}