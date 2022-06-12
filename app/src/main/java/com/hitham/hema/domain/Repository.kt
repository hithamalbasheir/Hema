package com.hitham.hema.domain

import com.hitham.hema.data.NewsDao
import com.hitham.hema.data.NewsRetrofitService
import com.hitham.hema.domain.model.News
import com.hitham.hema.domain.model.Response
import io.reactivex.Observable
import javax.inject.Inject

class Repository
@Inject
constructor(private val newsRetrofitService: NewsRetrofitService, private val newsDao: NewsDao) {
    fun getNews():Observable<Response>{
        return newsRetrofitService.getNews("ae","22d9c2c535dd4b0daf6a2d110e50f1d2")
    }
    fun getNewsForCaching(): Observable<Response>{
        return newsRetrofitService.getNewsForCaching("ae",
            "22d9c2c535dd4b0daf6a2d110e50f1d2",
            10,
            "publishedAt")
    }
    fun getCachedNews(): List<News>{
        return newsDao.getNews()
    }
    fun insertNews(news: List<News>){
        return newsDao.insertNews(news)
    }

    fun deleteNews() {
        return newsDao.deleteNews()
    }
}
