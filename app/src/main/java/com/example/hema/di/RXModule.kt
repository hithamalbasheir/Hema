package com.example.hema.di

import com.example.hema.di.scopes.IoScheduler
import com.example.hema.di.scopes.UIScheduler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
class RXModule {
    @Singleton
    @IoScheduler
    @Provides
    fun provideIoScheduler() : Scheduler {
        return Schedulers.io()
    }

    @Singleton
    @UIScheduler
    @Provides
    fun provideUIScheduler() : Scheduler {
        return AndroidSchedulers.mainThread()
    }
}