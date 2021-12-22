package com.christidischristos.kinandcarta.di

import android.content.Context
import androidx.room.Room
import com.christidischristos.kinandcarta.database.CaseStudyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): CaseStudyDatabase {
        return Room.databaseBuilder(
            context,
            CaseStudyDatabase::class.java,
            "case_studies"
        ).build()
    }

    @Singleton
    @Provides
    fun provideDao(db: CaseStudyDatabase) = db.dao
}
