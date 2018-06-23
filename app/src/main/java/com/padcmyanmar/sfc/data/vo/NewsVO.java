package com.padcmyanmar.sfc.data.vo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;
import com.padcmyanmar.sfc.persistence.typeconvertors.NewsImagesTypeConvertor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aung on 12/2/17.
 */
@Entity(tableName = "news")
@TypeConverters(NewsImagesTypeConvertor.class)
public class NewsVO {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @SerializedName("news-id")
    @ColumnInfo(name = "news_id")
    private String newsId;

    @SerializedName("brief")
    @ColumnInfo(name = "brief")
    private String brief;

    @SerializedName("details")
    @ColumnInfo(name = "details")
    private String details;

    @SerializedName("images")
    @ColumnInfo(name = "images")
    private List<String> images;

    @SerializedName("posted-date")
    @ColumnInfo(name = "posted_date")
    private String postedDate;

    @SerializedName("publication")
    @Ignore
    private PublicationVO publication;

    @ColumnInfo(name = "publication_id")
    private String publicationId;

    @SerializedName("favorites")
    @Ignore
    private List<FavoriteActionVO> favoriteActions;

    @SerializedName("comments")
    @Ignore
    private List<CommentActionVO> commentActions;

    @SerializedName("sent-tos")
    @Ignore
    private List<SentToVO> sentToActions;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
        if (images == null)
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
        if(favoriteActions == null)
            return new ArrayList<>();
        return favoriteActions;
    }

    public List<CommentActionVO> getCommentActions() {
        if(commentActions == null)
            return new ArrayList<>();
        return commentActions;
    }

    public List<SentToVO> getSentToActions() {
        if(sentToActions == null)
            return new ArrayList<>();
        return sentToActions;
    }

    public String getPublicationId() {
        if (TextUtils.isEmpty(publicationId)) {
            publicationId = publication.getPublicationId();
        }
        return publicationId;
    }

    public void setPublicationId(String publicationId) {
        this.publicationId = publicationId;
    }

    public void setNewsId(@NonNull String newsId) {
        this.newsId = newsId;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

    public void setPublication(PublicationVO publication) {
        this.publication = publication;
    }

    public void setFavoriteActions(List<FavoriteActionVO> favoriteActions) {
        this.favoriteActions = favoriteActions;
    }

    public void setCommentActions(List<CommentActionVO> commentActions) {
        this.commentActions = commentActions;
    }

    public void setSentToActions(List<SentToVO> sentToActions) {
        this.sentToActions = sentToActions;
    }
}
