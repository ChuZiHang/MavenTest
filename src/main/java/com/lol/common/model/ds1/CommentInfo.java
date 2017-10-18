package com.lol.common.model.ds1;

import java.io.Serializable;
import java.util.Date;

public class CommentInfo implements Serializable {
    private Integer id;

    private Long memberId;

    private Date createTime;

    private Integer scores;

    private Date replyTime;

    private Integer status;

    private Long objectId;

    private Integer objectType;

    private Integer sysCustId;

    private Integer parentCustId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getScores() {
        return scores;
    }

    public void setScores(Integer scores) {
        this.scores = scores;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public Integer getSysCustId() {
        return sysCustId;
    }

    public void setSysCustId(Integer sysCustId) {
        this.sysCustId = sysCustId;
    }

    public Integer getParentCustId() {
        return parentCustId;
    }

    public void setParentCustId(Integer parentCustId) {
        this.parentCustId = parentCustId;
    }
}