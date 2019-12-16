package com.newsapp.dagger.component

import com.newsapp.dagger.module.NewsModule
import com.newsapp.feature.main.NewsActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NewsModule::class])
interface NewsComponent {
    fun inject(newsBaseActivity: NewsActivity)
}