package com.newsapp.application

import android.app.Application
import com.newsapp.dagger.component.DaggerNewsComponent
import com.newsapp.dagger.component.NewsComponent
import com.newsapp.dagger.module.NewsModule

class NewsApplication : Application() {
    private lateinit var newsComponent: NewsComponent

    override fun onCreate() {
        super.onCreate()
        initNewsComponent()
    }

    private fun initNewsComponent() {
        newsComponent = DaggerNewsComponent.builder()
            .newsModule(NewsModule(this))
            .build()
    }

    fun getNewsComponent() = newsComponent
}