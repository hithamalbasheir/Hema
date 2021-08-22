package com.example.hema.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hema.domain.model.News
import com.example.hema.domain.model.Source

@Database(entities = [News::class], version = 4, exportSchema = false)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao
}