package com.padcmyanmar.sfc.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by aung on 12/16/17.
 */

public class MMNewsDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "mmNews.db";
    private static final int DB_VERSION = 1;

    private static final String SQL_CREATE_NEWS = "CREATE TABLE " + MMNewsContract.NewsEntry.TABLE_NAME + " (" +
            MMNewsContract.NewsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MMNewsContract.NewsEntry.COLUMN_NEWS_ID + " VARCHAR(256), " +
            MMNewsContract.NewsEntry.COLUMN_BRIEF + " TEXT, " +
            MMNewsContract.NewsEntry.COLUMN_DETAILS + " TEXT, " +
            MMNewsContract.NewsEntry.COLUMN_POSTED_DATE + " TEXT, " +
            MMNewsContract.NewsEntry.COLUMN_PUBLICATION_ID + " TEXT, " +

            " UNIQUE (" + MMNewsContract.NewsEntry.COLUMN_NEWS_ID + ") ON CONFLICT REPLACE" +
            ");";

    private static final String SQL_CREATE_IMAGES_IN_NEWS = "CREATE TABLE " + MMNewsContract.ImagesInNewsEntry.TABLE_NAME + " (" +
            MMNewsContract.ImagesInNewsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MMNewsContract.ImagesInNewsEntry.COLUMN_NEWS_ID + " VARCHAR(256), " +
            MMNewsContract.ImagesInNewsEntry.COLUMN_IMAGE_URL + " TEXT, " +

            " UNIQUE (" + MMNewsContract.ImagesInNewsEntry.COLUMN_IMAGE_URL + ") ON CONFLICT REPLACE" +
            ");";

    private static final String SQL_CREATE_PUBLICATIONS = "CREATE TABLE " + MMNewsContract.PublicationEntry.TABLE_NAME + " (" +
            MMNewsContract.PublicationEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MMNewsContract.PublicationEntry.COLUMN_PUBLICATION_ID + " VARCHAR(256), " +
            MMNewsContract.PublicationEntry.COLUMN_TITLE + " TEXT, " +
            MMNewsContract.PublicationEntry.COLUMN_LOGO + " TEXT, " +

            " UNIQUE (" + MMNewsContract.PublicationEntry.COLUMN_PUBLICATION_ID + ") ON CONFLICT REPLACE" +
            ");";

    private static final String SQL_CREATE_FAVORITE_ACTIONS = "CREATE TABLE " + MMNewsContract.FavoriteActionEntry.TABLE_NAME + " (" +
            MMNewsContract.FavoriteActionEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MMNewsContract.FavoriteActionEntry.COLUMN_FAVORITE_ID + " VARCHAR(256), " +
            MMNewsContract.FavoriteActionEntry.COLUMN_FAVORITE_DATE + " TEXT, " +
            MMNewsContract.FavoriteActionEntry.COLUMN_NEWS_ID + " VARCHAR(256), " +
            MMNewsContract.FavoriteActionEntry.COLUMN_USER_ID + " VARCHAR(256), " +

            " UNIQUE (" + MMNewsContract.FavoriteActionEntry.COLUMN_FAVORITE_ID + ") ON CONFLICT REPLACE" +
            ");";

    private static final String SQL_CREATE_COMMENT_ACTIONS = "CREATE TABLE " + MMNewsContract.CommentActionEntry.TABLE_NAME + " (" +
            MMNewsContract.CommentActionEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MMNewsContract.CommentActionEntry.COLUMN_COMMENT_ID + " VARCHAR(256), " +
            MMNewsContract.CommentActionEntry.COLUMN_COMMENT + " TEXT, " +
            MMNewsContract.CommentActionEntry.COLUMN_COMMENT_DATE + " TEXT, " +
            MMNewsContract.CommentActionEntry.COLUMN_NEWS_ID + " VARCHAR(256), " +
            MMNewsContract.CommentActionEntry.COLUMN_USER_ID + " VARCHAR(256), " +

            " UNIQUE (" + MMNewsContract.CommentActionEntry.COLUMN_COMMENT_ID + ") ON CONFLICT REPLACE" +
            ");";

    private static final String SQL_CREATE_SENT_TO_ACTIONS = "CREATE TABLE " + MMNewsContract.SentToActionEntry.TABLE_NAME + " (" +
            MMNewsContract.SentToActionEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MMNewsContract.SentToActionEntry.COLUMN_SENT_TO_ID + " VARCHAR(256), " +
            MMNewsContract.SentToActionEntry.COLUMN_SENT_DATE + " TEXT, " +
            MMNewsContract.SentToActionEntry.COLUMN_SENDER_ID + " VARCHAR(256), " +
            MMNewsContract.SentToActionEntry.COLUMN_RECEIVER_ID + " VARCHAR(256), " +

            " UNIQUE (" + MMNewsContract.SentToActionEntry.COLUMN_SENT_TO_ID + ") ON CONFLICT REPLACE" +
            ");";

    private static final String SQL_CREATE_ACTED_USER = "CREATE TABLE " + MMNewsContract.ActedUserEntry.TABLE_NAME + " (" +
            MMNewsContract.ActedUserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MMNewsContract.ActedUserEntry.COLUMN_USER_ID + " VARCHAR(256), " +
            MMNewsContract.ActedUserEntry.COLUMN_USER_NAME + " TEXT, " +
            MMNewsContract.ActedUserEntry.COLUMN_PROFILE_IMAGE + " TEXT, " +

            " UNIQUE (" + MMNewsContract.ActedUserEntry.COLUMN_USER_ID + ") ON CONFLICT REPLACE" +
            ");";


    public MMNewsDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ACTED_USER);
        db.execSQL(SQL_CREATE_PUBLICATIONS);
        db.execSQL(SQL_CREATE_NEWS);
        db.execSQL(SQL_CREATE_IMAGES_IN_NEWS);

        db.execSQL(SQL_CREATE_FAVORITE_ACTIONS);
        db.execSQL(SQL_CREATE_COMMENT_ACTIONS);
        db.execSQL(SQL_CREATE_SENT_TO_ACTIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MMNewsContract.SentToActionEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MMNewsContract.CommentActionEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MMNewsContract.FavoriteActionEntry.TABLE_NAME);

        db.execSQL("DROP TABLE IF EXISTS " + MMNewsContract.ImagesInNewsEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MMNewsContract.NewsEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MMNewsContract.PublicationEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MMNewsContract.ActedUserEntry.TABLE_NAME);

        onCreate(db);
    }
}
