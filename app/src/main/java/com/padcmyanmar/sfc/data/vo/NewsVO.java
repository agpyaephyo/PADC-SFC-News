package com.padcmyanmar.sfc.data.vo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aung on 12/2/17.
 */
@Entity(tableName = "news", foreignKeys = {
        @ForeignKey(entity = PublicationVO.class, parentColumns = "id", childColumns = "publication-id",deferred = true),
})
public class NewsVO {

    @PrimaryKey
    @SerializedName("news-id")
    @NonNull
    private String newsId;

    @SerializedName("brief")
    private String brief;

    @SerializedName("details")
    private String details;

    @SerializedName("images")
    private List<String> images;

    @SerializedName("posted-date")
    private String postedDate;

    @Ignore
    @SerializedName("publication")
    private PublicationVO publication;

    @ColumnInfo(name = "publication-id")
    private String publicationId;

    public String getPublicationId() {
        if(publication != null) {
            return publication.getPublicationId();
        }
        return null;
    }

    /*
    public void setPublicationId(String publicationId) {
        this.publicationId = publicationId;
    }
    */

    @SerializedName("favorites")
    private List<FavoriteActionVO> favoriteActions;

    @SerializedName("comments")
    private List<CommentActionVO> commentActions;

    @SerializedName("sent-tos")
    private List<SentToVO> sentToActions;

    public String getNewsId() {
        return newsId;
    }

    public String getBrief() {
        return brief;
    }

    public String getDetails() {
        return details;
    }

    public List<String> getImages() {
        if(images == null)
            return new ArrayList<>();

        return images;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public PublicationVO getPublication() {
        return publication;
    }

    public List<FavoriteActionVO> getFavoriteActions() {
        return favoriteActions;
    }

    public List<CommentActionVO> getCommentActions() {
        return commentActions;
    }

    public List<SentToVO> getSentToActions() {
        return sentToActions;
    }
}
