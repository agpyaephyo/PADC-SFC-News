package com.padcmyanmar.sfc.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.padcmyanmar.sfc.data.vo.FavoriteActionVO;

import java.util.List;

@Dao
public interface FavoriteActionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertFavoriteActions(FavoriteActionVO... favoriteActionVOS);

    @Query("SELECT * FROM favorites WHERE news_id = :newsId")
    List<FavoriteActionVO> getFavoriteActionsByNewsId(String newsId);
}
