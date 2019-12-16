package com.newsapp.dagger.module

import android.content.Context
import com.newsapp.feature.main.NewsAdapter
import com.newsapp.network.NewsApiService
import com.newsapp.network.NewsRetrofitHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NewsModule(private val context: Context) {
    @Singleton
    @Provides
    fun provideApplicationContext(): Context = context

    @Singleton
    @Provides
    fun provideApiService(): NewsApiService = NewsRetrofitHelper().getApiService()

    @Provides
    fun provideMainAdapter(): NewsAdapter = NewsAdapter()
}