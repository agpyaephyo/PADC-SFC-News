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
@Entity(tableName = "favorites")
public class FavoriteActionVO {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @SerializedName("favorite-id")
    @ColumnInfo(name = "favorite_id")
    private String favoriteId;

    @SerializedName("favorite-date")
    @ColumnInfo(name = "favorite_date")
    private String favoriteDate;

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

    public String getFavoriteId() {
        return favoriteId;
    }

    public String getFavoriteDate() {
        return favoriteDate;
    }

    public ActedUserVO getActedUser() {
        return actedUser;
    }

    public void setFavoriteId(String favoriteId) {
        this.favoriteId = favoriteId;
    }

    public void setFavoriteDate(String favoriteDate) {
        this.favoriteDate = favoriteDate;
    }

    public void setActedUser(ActedUserVO actedUser) {
        this.actedUser = actedUser;
    }

    public void setActedUserId(String actedUserId) {
        this.actedUserId = actedUserId;
    }

    public String getActedUserId() {
        if (TextUtils.isEmpty(actedUserId)) {
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
