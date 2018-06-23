package com.padcmyanmar.sfc.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.padcmyanmar.sfc.data.vo.ActedUserVO;
import com.padcmyanmar.sfc.data.vo.NewsVO;
import com.padcmyanmar.sfc.data.vo.PublicationVO;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertNews(NewsVO... newsVO);

    @Query("SELECT * FROM news")
    List<NewsVO> getAllNews();

    @Query("SELECT * FROM news WHERE news_id = :newsId")
    NewsVO getNewsById(String newsId);
}
