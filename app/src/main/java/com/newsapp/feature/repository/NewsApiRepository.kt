package com.newsapp.feature.repository

import android.content.Context
import androidx.room.Room
import com.newsapp.feature.shared.model.Articles
import com.newsapp.network.NewsApiService
import com.newsapp.room.NewsAppDatabase
import com.newsapp.utils.constants.NewsApiContants
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsApiRepository @Inject constructor() {
    @Inject
    lateinit var apiService: NewsApiService

    @Inject
    lateinit var context: Context

    //get data from db, if db empty ... get data from Api
    fun getArticles(): Single<List<Articles>> = getArticlesFromDb()

    private fun getArticlesFromDb(): Single<List<Articles>> =
        Single.create { e ->
            val appDatabase = Room.databaseBuilder(context, NewsAppDatabase::class.java, "articles").build()
            val newsDao = appDatabase.newsDao()
            newsDao.getAllArticles()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ articlesList ->
                    if (articlesList.isNotEmpty()) {
                        e.onSuccess(articlesList)
                    } else {
                        getArticlesFromApi(e)
                    }
                }, {
                    getArticlesFromApi(e)
                })
        }

    private fun insertToDatabase(articlesList: List<Articles>) =
        Single.create<String> { e ->
            val appDatabase = Room.databaseBuilder(context, NewsAppDatabase::class.java, "articles").build()
            val newsDao = appDatabase.newsDao()
            //insert user to Room
            try {
                newsDao.insetArticles(articlesList)
                e.onSuccess("Successful")
            } catch (exception: IllegalStateException) {
                e.onError(exception)
            }
        }

    private fun getArticlesFromApi(emitter: SingleEmitter<List<Articles>>) =
        apiService.getArticles(NewsApiContants.sources, NewsApiContants.apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                if (response.isSuccessful) {
                    insertToDatabase(response.body()!!.articles)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe({
                            emitter.onSuccess(response.body()!!.articles)
                        }, { error ->
                            emitter.onError(error)
                        })
                } else
                    emitter.onError(Throwable("Error API call"))
            }, { error ->
                emitter.onError(error)
            })
}
