package com.lol.common.model.ds1;

import java.io.Serializable;

public class CommentInfoWithBLOBs extends CommentInfo implements Serializable {
    private String commentCon;

    private String reply;

    private static final long serialVersionUID = 1L;

    public String getCommentCon() {
        return commentCon;
    }

    public void setCommentCon(String commentCon) {
        this.commentCon = commentCon == null ? null : commentCon.trim();
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply == null ? null : reply.trim();
    }
}