package com.padcmyanmar.sfc.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.padcmyanmar.sfc.data.vo.CommentActionVO;
import com.padcmyanmar.sfc.data.vo.FavoriteActionVO;

import java.util.List;

@Dao
public interface CommentActionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    String[] insertCommentActions(CommentActionVO... commentActionVOS);

    @Query("SELECT * FROM comments WHERE news_id = :newsId")
    List<CommentActionVO> getCommentActionsByNewsId(String newsId);
}
