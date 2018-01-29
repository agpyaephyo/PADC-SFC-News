package com.padcmyanmar.sfc.data.vo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.padcmyanmar.sfc.persistence.MMNewsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aung on 12/2/17.
 */

public class NewsVO {

    @SerializedName("news-id")
    private String newsId;

    @SerializedName("brief")
    private String brief;

    @SerializedName("details")
    private String details;

    @SerializedName("images")
    private List<String> images;

    @SerializedName("posted-date")
    private String postedDate;

    @SerializedName("publication")
    private PublicationVO publication;

    @SerializedName("favorites")
    private List<FavoriteActionVO> favoriteActions;

    @SerializedName("comments")
    private List<CommentActionVO> commentActions;

    @SerializedName("sent-tos")
    private List<SentToVO> sentToActions;

    public NewsVO() {
    }

    public NewsVO(String brief, String details, List<String> images, String postedDate) {
        this.newsId = String.valueOf(System.currentTimeMillis() / 1000);
        this.brief = brief;
        this.details = details;
        this.images = images;
        this.postedDate = postedDate;
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
            images = new ArrayList<>();

        return images;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public PublicationVO getPublication() {
        return publication;
    }

    public void setPublication(PublicationVO publication) {
        this.publication = publication;
    }

    public List<FavoriteActionVO> getFavoriteActions() {
        if (favoriteActions == null)
            favoriteActions = new ArrayList<>();

        return favoriteActions;
    }

    public List<CommentActionVO> getCommentActions() {
        if (commentActions == null)
            return new ArrayList<>();

        return commentActions;
    }

    public List<SentToVO> getSentToActions() {
        if (sentToActions == null)
            return new ArrayList<>();

        return sentToActions;
    }

    public ContentValues parseToContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MMNewsContract.NewsEntry.COLUMN_NEWS_ID, newsId);
        contentValues.put(MMNewsContract.NewsEntry.COLUMN_BRIEF, brief);
        contentValues.put(MMNewsContract.NewsEntry.COLUMN_DETAILS, details);
        contentValues.put(MMNewsContract.NewsEntry.COLUMN_POSTED_DATE, postedDate);
        if (publication != null) {
            contentValues.put(MMNewsContract.NewsEntry.COLUMN_PUBLICATION_ID, publication.getPublicationId());
        }
        return contentValues;
    }

    public static NewsVO parseFromCursor(Context context, Cursor cursor) {
        NewsVO news = new NewsVO();
        news.newsId = cursor.getString(cursor.getColumnIndex(MMNewsContract.NewsEntry.COLUMN_NEWS_ID));
        news.brief = cursor.getString(cursor.getColumnIndex(MMNewsContract.NewsEntry.COLUMN_BRIEF));
        news.details = cursor.getString(cursor.getColumnIndex(MMNewsContract.NewsEntry.COLUMN_DETAILS));
        news.postedDate = cursor.getString(cursor.getColumnIndex(MMNewsContract.NewsEntry.COLUMN_POSTED_DATE));

        news.publication = PublicationVO.parseFromCursor(cursor);
        news.images = loadImagesInNews(context, news.newsId);
        news.favoriteActions = loadFavoriteActionsInNews(context, news.newsId);
        news.sentToActions = loadSentToActionsInNews(context, news.newsId);

        return news;
    }

    private static List<FavoriteActionVO> loadFavoriteActionsInNews(Context context, String newsId) {
        Cursor favoriteActionsInNewsCursor = context.getContentResolver().query(MMNewsContract.FavoriteActionEntry.CONTENT_URI,
                null,
                MMNewsContract.FavoriteActionEntry.COLUMN_NEWS_ID + " = ?", new String[]{newsId},
                null);

        if (favoriteActionsInNewsCursor != null && favoriteActionsInNewsCursor.moveToFirst()) {
            List<FavoriteActionVO> favoriteActions = new ArrayList<>();
            do {
                favoriteActions.add(FavoriteActionVO.parseFromCursor(favoriteActionsInNewsCursor));
            } while (favoriteActionsInNewsCursor.moveToNext());
            favoriteActionsInNewsCursor.close();
            return favoriteActions;
        }
        return null;
    }

    private static List<SentToVO> loadSentToActionsInNews(Context context, String newsId) {
        Cursor sentToActionsInNewsCursor = context.getContentResolver().query(MMNewsContract.SentToActionEntry.CONTENT_URI,
                null,
                MMNewsContract.FavoriteActionEntry.COLUMN_NEWS_ID + " = ?", new String[]{newsId},
                null);

        if (sentToActionsInNewsCursor != null && sentToActionsInNewsCursor.moveToFirst()) {
            List<SentToVO> sentToList = new ArrayList<>();
            do {
                sentToList.add(SentToVO.parseFromCursor(sentToActionsInNewsCursor));
            } while (sentToActionsInNewsCursor.moveToNext());
            sentToActionsInNewsCursor.close();
            return sentToList;
        }
        return null;
    }

    private static List<String> loadImagesInNews(Context context, String newsId) {
        Cursor imagesInNewsCursor = context.getContentResolver().query(MMNewsContract.ImagesInNewsEntry.CONTENT_URI,
                null,
                MMNewsContract.ImagesInNewsEntry.COLUMN_NEWS_ID + " = ?", new String[]{newsId},
                null);

        if (imagesInNewsCursor != null && imagesInNewsCursor.moveToFirst()) {
            List<String> imagesInNews = new ArrayList<>();
            do {
                imagesInNews.add(
                        imagesInNewsCursor.getString(
                                imagesInNewsCursor.getColumnIndex(MMNewsContract.ImagesInNewsEntry.COLUMN_IMAGE_URL)));
            } while (imagesInNewsCursor.moveToNext());
            imagesInNewsCursor.close();
            return imagesInNews;
        }
        return null;
    }
}
