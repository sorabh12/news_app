package com.newsapp.feature.main

import com.newsapp.feature.repository.NewsApiRepository
import javax.inject.Inject

class NewsInteractor @Inject constructor(private val articlesApiRepository: NewsApiRepository) {
    fun getArticlesApiRepository() = articlesApiRepository.getArticles()
}