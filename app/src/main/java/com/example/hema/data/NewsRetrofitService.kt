package com.example.hema.data

import com.example.hema.domain.model.News
import com.example.hema.domain.model.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface NewsRetrofitService {
    @GET("/v2/everything")
    fun getNews(@Query("q") query: String,@Query("apiKey") api_key: String) : Observable<Response>
}
