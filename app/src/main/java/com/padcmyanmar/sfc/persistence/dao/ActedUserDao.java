package com.padcmyanmar.sfc.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.padcmyanmar.sfc.data.vo.ActedUserVO;

@Dao
public interface ActedUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    String insertActedUser(ActedUserVO actedUser);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    String[] insertActedUsers(ActedUserVO... actedUsers);

    @Query("SELECT * FROM acted_users WHERE id = :userId")
    ActedUserVO getActedUserById(String userId);
}
