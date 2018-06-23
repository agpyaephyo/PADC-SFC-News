package com.padcmyanmar.sfc.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.padcmyanmar.sfc.data.vo.CommentActionVO;
import com.padcmyanmar.sfc.data.vo.SentToVO;

import java.util.List;

@Dao
public interface SentToActionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertSentToActions(SentToVO... sentToVOS);

    @Query("SELECT * FROM sent_tos WHERE news_id = :newsId")
    List<SentToVO> getSentTosByNewsId(String newsId);
}
