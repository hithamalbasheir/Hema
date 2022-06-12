package com.hitham.hema.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hitham.hema.domain.model.News

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)  fun insertNews(news: List<News>)
    @Query("SELECT * FROM news_table") fun getNews():List<News>
    @Query("DELETE FROM NEWS_TABLE ") fun deleteNews()
}