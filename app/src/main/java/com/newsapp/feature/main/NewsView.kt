package com.newsapp.feature.main

import com.newsapp.feature.base.NewsBaseView
import com.newsapp.feature.shared.model.Articles

interface NewsView: NewsBaseView {
    fun populateArticles(articlesList: List<Articles>)
}