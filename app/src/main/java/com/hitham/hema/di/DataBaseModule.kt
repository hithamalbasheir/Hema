package com.hitham.hema.di

import android.app.Application
import androidx.room.Room
import com.hitham.hema.data.NewsDao
import com.hitham.hema.data.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    @Singleton
    fun provideNewsDB(application: Application): NewsDatabase{
        return Room.databaseBuilder(application,NewsDatabase::class.java,"news_DB")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
    @Provides
    @Singleton
    fun provideDao(newsDatabase: NewsDatabase):NewsDao{
        return newsDatabase.newsDao()
    }
}