package com.padcmyanmar.sfc.data.vo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aung on 12/3/17.
 */
@Entity(tableName = "comments")
public class CommentActionVO {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @SerializedName("comment-id")
    @ColumnInfo(name = "comment_id")
    private String commentId;

    @SerializedName("comment")
    @ColumnInfo(name = "comment")
    private String comment;

    @SerializedName("comment-date")
    @ColumnInfo(name = "comment_date")
    private String commentDate;

    @SerializedName("acted-user")
    @Ignore
    private ActedUserVO actedUser;

    @ColumnInfo(name = "acted_user_id")
    private transient String actedUserId;

    @ColumnInfo(name = "news_id")
    private transient String newsId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCommentId() {
        return commentId;
    }

    public String getComment() {
        return comment;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public ActedUserVO getActedUser() {
        return actedUser;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public void setActedUser(ActedUserVO actedUser) {
        this.actedUser = actedUser;
    }

    public void setActedUserId(String actedUserId) {
        this.actedUserId = actedUserId;
    }

    public String getActedUserId() {
        if(TextUtils.isEmpty(actedUserId)) {
            actedUserId = actedUser.getUserId();
        }
        return actedUserId;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }
}
