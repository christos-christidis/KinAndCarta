package com.christidischristos.kinandcarta.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CaseStudyDao {

    @Query("select * from casestudyentity")
    fun getCaseStudies(): LiveData<List<CaseStudyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(caseStudies: List<CaseStudyEntity>)
}

