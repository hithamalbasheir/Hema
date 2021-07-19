package com.example.hema.di

import com.example.hema.data.NewsRetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideOkHTTPInterceptor(): OkHttpClient{
        return OkHttpClient.Builder().build()
    }
    @Provides
    @Singleton
    fun provideRetrofit():NewsRetrofitService{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://newsapi.org/")
            .client(provideOkHTTPInterceptor())
            .build()
            .create()
    }
}