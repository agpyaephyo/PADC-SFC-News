package com.padcmyanmar.sfc.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.padcmyanmar.sfc.data.vo.ActedUserVO;

@Dao
public interface ActedUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertActedUser(ActedUserVO actedUser);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertActedUsers(ActedUserVO... actedUsers);

    @Query("SELECT * FROM acted_users WHERE user_id = :userId")
    ActedUserVO getActedUserById(String userId);
}
