package com.padcmyanmar.sfc.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.padcmyanmar.sfc.data.vo.ActedUserVO;
import com.padcmyanmar.sfc.data.vo.PublicationVO;

@Dao
public interface PublicationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertPublication(PublicationVO publication);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertPublications(PublicationVO... publications);

    @Query("SELECT * FROM publication WHERE publication_id = :publicationId")
    PublicationVO getPublicationById(String publicationId);
}
