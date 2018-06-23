package com.padcmyanmar.sfc.data.vo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aung on 12/3/17.
 */

@Entity(tableName = "publication")
public class PublicationVO {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @SerializedName("publication-id")
    @ColumnInfo(name = "publication_id")
    private String publicationId;

    @SerializedName("title")
    @ColumnInfo(name = "title")
    private String title;

    @SerializedName("logo")
    @ColumnInfo(name = "logo")
    private String logo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPublicationId() {
        return publicationId;
    }

    public String getTitle() {
        return title;
    }

    public String getLogo() {
        return logo;
    }

    public void setPublicationId(@NonNull String publicationId) {
        this.publicationId = publicationId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
