package com.padcmyanmar.sfc.data.vo;

import android.content.ContentValues;

import com.google.gson.annotations.SerializedName;
import com.padcmyanmar.sfc.persistence.MMNewsContract;

/**
 * Created by aung on 12/3/17.
 */

public class CommentActionVO {

    @SerializedName("comment-id")
    private String commentId;

    @SerializedName("comment")
    private String comment;

    @SerializedName("comment-date")
    private String commentDate;

    @SerializedName("acted-user")
    private ActedUserVO actedUser;

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

    public ContentValues parseToContentValues(String newsId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MMNewsContract.CommentActionEntry.COLUMN_COMMENT_ID, commentId);
        contentValues.put(MMNewsContract.CommentActionEntry.COLUMN_COMMENT, comment);
        contentValues.put(MMNewsContract.CommentActionEntry.COLUMN_COMMENT_DATE, commentDate);
        contentValues.put(MMNewsContract.CommentActionEntry.COLUMN_USER_ID, actedUser.getUserId());
        contentValues.put(MMNewsContract.CommentActionEntry.COLUMN_NEWS_ID, newsId);
        return contentValues;
    }
}
