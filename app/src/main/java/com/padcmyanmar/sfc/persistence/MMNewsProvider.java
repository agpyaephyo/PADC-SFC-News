package com.padcmyanmar.sfc.persistence;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by aung on 12/16/17.
 */

public class MMNewsProvider extends ContentProvider {

    public static final int ACTED_USERS = 100;
    public static final int PUBLICATIONS = 200;
    public static final int NEWS = 300;
    public static final int IMAGES_IN_NEWS = 400;
    public static final int FAVORITE_ACTIONS = 500;
    public static final int COMMENT_ACTIONS = 600;
    public static final int SENT_TO_ACTIONS = 700;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private MMNewsDBHelper mDBHelper;

    private static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(MMNewsContract.CONTENT_AUTHORITY, MMNewsContract.PATH_ACTED_USERS, ACTED_USERS);
        uriMatcher.addURI(MMNewsContract.CONTENT_AUTHORITY, MMNewsContract.PATH_PUBLICATIONS, PUBLICATIONS);
        uriMatcher.addURI(MMNewsContract.CONTENT_AUTHORITY, MMNewsContract.PATH_NEWS, NEWS);
        uriMatcher.addURI(MMNewsContract.CONTENT_AUTHORITY, MMNewsContract.PATH_IMAGES_IN_NEWS, IMAGES_IN_NEWS);
        uriMatcher.addURI(MMNewsContract.CONTENT_AUTHORITY, MMNewsContract.PATH_FAVORITE_ACTIONS, FAVORITE_ACTIONS);
        uriMatcher.addURI(MMNewsContract.CONTENT_AUTHORITY, MMNewsContract.PATH_COMMENT_ACTIONS, COMMENT_ACTIONS);
        uriMatcher.addURI(MMNewsContract.CONTENT_AUTHORITY, MMNewsContract.PATH_SENT_TO_ACTIONS, SENT_TO_ACTIONS);
        return uriMatcher;
    }

    private String getTableName(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case ACTED_USERS:
                return MMNewsContract.ActedUserEntry.TABLE_NAME;
            case PUBLICATIONS:
                return MMNewsContract.PublicationEntry.TABLE_NAME;
            case NEWS:
                return MMNewsContract.NewsEntry.TABLE_NAME;
            case IMAGES_IN_NEWS:
                return MMNewsContract.ImagesInNewsEntry.TABLE_NAME;
            case FAVORITE_ACTIONS:
                return MMNewsContract.FavoriteActionEntry.TABLE_NAME;
            case COMMENT_ACTIONS:
                return MMNewsContract.CommentActionEntry.TABLE_NAME;
            case SENT_TO_ACTIONS:
                return MMNewsContract.SentToActionEntry.TABLE_NAME;
        }
        return null;
    }

    private Uri getContentUri(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case ACTED_USERS:
                return MMNewsContract.ActedUserEntry.CONTENT_URI;
            case PUBLICATIONS:
                return MMNewsContract.PublicationEntry.CONTENT_URI;
            case NEWS:
                return MMNewsContract.NewsEntry.CONTENT_URI;
            case IMAGES_IN_NEWS:
                return MMNewsContract.ImagesInNewsEntry.CONTENT_URI;
            case FAVORITE_ACTIONS:
                return MMNewsContract.FavoriteActionEntry.CONTENT_URI;
            case COMMENT_ACTIONS:
                return MMNewsContract.CommentActionEntry.CONTENT_URI;
            case SENT_TO_ACTIONS:
                return MMNewsContract.SentToActionEntry.CONTENT_URI;
        }
        return null;
    }

    @Override
    public boolean onCreate() {
        mDBHelper = new MMNewsDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor queryCursor = mDBHelper.getReadableDatabase().query(getTableName(uri),
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        if (getContext() != null) {
            queryCursor.setNotificationUri(getContext().getContentResolver(), uri);
        }

        return queryCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case ACTED_USERS:
                return MMNewsContract.ActedUserEntry.DIR_TYPE;
            case PUBLICATIONS:
                return MMNewsContract.PublicationEntry.DIR_TYPE;
            case NEWS:
                return MMNewsContract.NewsEntry.DIR_TYPE;
            case IMAGES_IN_NEWS:
                return MMNewsContract.ImagesInNewsEntry.DIR_TYPE;
            case FAVORITE_ACTIONS:
                return MMNewsContract.FavoriteActionEntry.DIR_TYPE;
            case COMMENT_ACTIONS:
                return MMNewsContract.CommentActionEntry.DIR_TYPE;
            case SENT_TO_ACTIONS:
                return MMNewsContract.SentToActionEntry.DIR_TYPE;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        long _id = db.insert(tableName, null, values);
        if (_id > 0) {
            Uri tableContentUri = getContentUri(uri);
            Uri insertedUri = ContentUris.withAppendedId(tableContentUri, _id);

            if (getContext() != null) {
                getContext().getContentResolver().notifyChange(uri, null);
            }

            return insertedUri;
        }

        return null;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        int insertedCount = 0;

        try {
            db.beginTransaction();
            for (ContentValues cv : values) {
                long _id = db.insert(tableName, null, cv);
                if (_id > 0) {
                    insertedCount++;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }

        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }

        return insertedCount;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int rowDeleted;
        String tableName = getTableName(uri);

        rowDeleted = db.delete(tableName, selection, selectionArgs);
        Context context = getContext();
        if (context != null && rowDeleted > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int rowUpdated;
        String tableName = getTableName(uri);

        rowUpdated = db.update(tableName, contentValues, selection, selectionArgs);
        Context context = getContext();
        if (context != null && rowUpdated > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowUpdated;
    }
}
