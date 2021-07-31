package com.example.hema.domain

import com.example.hema.data.NewsRetrofitService
import com.example.hema.domain.model.News
import com.example.hema.domain.model.Response
import io.reactivex.Observable
import javax.inject.Inject

class Repository @Inject constructor (private val newsRetrofitService: NewsRetrofitService) {
    fun getNews():Observable<Response>{
        return newsRetrofitService.getNews("ae","22d9c2c535dd4b0daf6a2d110e50f1d2")
    }
}
