package com.padcmyanmar.sfc.data.vo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aung on 12/3/17.
 */
@Entity(tableName = "acted_users")
public class ActedUserVO {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @SerializedName("user-id")
    @ColumnInfo(name = "user_id")
    private String userId;

    @SerializedName("user-name")
    @ColumnInfo(name = "user_name")
    private String userName;

    @SerializedName("profile-image")
    @ColumnInfo(name = "profile_image")
    private String profileImage;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
