package com.hitham.hema.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hitham.hema.domain.model.News

@Database(entities = [News::class], version = 4, exportSchema = false)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao
}