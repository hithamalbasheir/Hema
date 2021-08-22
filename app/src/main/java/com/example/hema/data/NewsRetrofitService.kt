package com.example.hema.data

import com.example.hema.domain.model.News
import com.example.hema.domain.model.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface NewsRetrofitService {
    @GET("/v2/top-headlines")
    fun getNews(@Query("country") country: String, @Query("apiKey") api_key: String) : Observable<Response>
    @GET("/v2/top-headlines")
    fun getNewsForCaching(@Query("country") country: String,
                          @Query("apiKey") api_key: String,
                          @Query("pageSize") pageSize: Int,
                          @Query("sortBy") sortBy: String): Observable<Response>
}
