package com.newsapp.feature.main

import android.util.Log
import com.newsapp.feature.base.NewsBasePresenter
import javax.inject.Inject

class NewsPresenter @Inject constructor(private val newsInteractor: NewsInteractor) : NewsBasePresenter<NewsView>() {
    fun getArticles() {
        ifViewAttached { view ->
            view.showLoading()
            newsInteractor.getArticlesApiRepository().subscribe({ articles ->
                view.populateArticles(articles)
                view.hideLoading()
            }, { error ->
                Log.d("Xais", error.localizedMessage)
                view.hideLoading()
            })
        }
    }
}