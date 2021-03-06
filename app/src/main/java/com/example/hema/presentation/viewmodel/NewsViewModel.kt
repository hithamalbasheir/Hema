package com.example.hema.presentation.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hema.di.scopes.IoScheduler
import com.example.hema.di.scopes.UIScheduler
import com.example.hema.domain.Repository
import com.example.hema.domain.model.News
import com.example.hema.domain.model.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: Repository, @IoScheduler val ioScheduler: Scheduler, @UIScheduler private val uiScheduler: Scheduler): ViewModel() {
    val newsList: MutableLiveData<List<News>> = MutableLiveData()
    val cachingList: MutableLiveData<List<News>> = MutableLiveData()
    val cachedList: MutableLiveData<List<News>> = MutableLiveData()
    private val compositeDisposable =  CompositeDisposable()

    fun getNews() {
        val subscription = repository.getNews()
            .subscribeOn(ioScheduler)
            .observeOn(uiScheduler)
            .subscribe({ it ->
                newsList.value = it.articles
                Log.d("TAG", "getNews: $it")
            }, { it ->
                Log.e("viewmodel",
                        it.message.toString())
            })
        compositeDisposable.add(subscription)
    }
    fun getNewsForCaching() {
        val subscription = repository.getNewsForCaching()
            .subscribeOn(ioScheduler)
            .observeOn(uiScheduler)
            .subscribe({ it ->
                cachingList.value = it.articles
                Log.d("TAG", "getNewsForCaching: $it")
            }, { it ->
                Log.e("viewmodel",
                        it.message.toString())
            })
        compositeDisposable.add(subscription)
    }
    fun insertNews(news: List<News>){
        return repository.insertNews(news)
    }
    fun deleteNews(){
        return repository.deleteNews()
    }
    fun getCachedNews(){
        cachedList.value = repository.getCachedNews()
    }
    fun onDestroy(){
        compositeDisposable.dispose()
    }
}