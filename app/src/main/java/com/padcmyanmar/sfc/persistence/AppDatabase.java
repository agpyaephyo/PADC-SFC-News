package com.padcmyanmar.sfc.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.padcmyanmar.sfc.data.vo.ActedUserVO;
import com.padcmyanmar.sfc.data.vo.CommentActionVO;
import com.padcmyanmar.sfc.data.vo.FavoriteActionVO;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.data.vo.PublicationVO;
import com.padcmyanmar.sfc.data.vo.SentToVO;
import com.padcmyanmar.sfc.persistence.dao.ActedUserDao;
import com.padcmyanmar.sfc.persistence.dao.CommentActionDao;
import com.padcmyanmar.sfc.persistence.dao.FavoriteActionDao;
import com.padcmyanmar.sfc.persistence.dao.NewsDao;
import com.padcmyanmar.sfc.persistence.dao.PublicationDao;
import com.padcmyanmar.sfc.persistence.dao.SentToActionDao;

import org.w3c.dom.Comment;

@Database(entities = {
        ActedUserVO.class,
        FavoriteActionVO.class, CommentActionVO.class, SentToVO.class,
        PublicationVO.class, NewsVO.class
}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "SFC-News.DB";
    private static AppDatabase INSTANCE;

    public abstract ActedUserDao actedUserDao();
    public abstract FavoriteActionDao favoriteActionDao();
    public abstract CommentActionDao commentActionDao();
    public abstract SentToActionDao sentToActionDao();
    public abstract PublicationDao publicationDao();
    public abstract NewsDao newsDao();

    public static AppDatabase getDatabase(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                    .allowMainThreadQueries() //Remove this after testing. Access to DB should always be from background thread.
                    .build();
        }
        return INSTANCE;
    }
}
