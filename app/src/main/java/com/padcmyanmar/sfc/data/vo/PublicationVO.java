package com.padcmyanmar.sfc.data.vo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aung on 12/3/17.
 */

@Entity(tableName = "publication")
public class PublicationVO {

    @SerializedName("publication-id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String publicationId;

    @SerializedName("title")
    @ColumnInfo(name = "title")
    private String title;

    @SerializedName("logo")
    @ColumnInfo(name = "logo")
    private String logo;

    public String getPublicationId() {
        return publicationId;
    }

    public String getTitle() {
        return title;
    }

    public String getLogo() {
        return logo;
    }
}
