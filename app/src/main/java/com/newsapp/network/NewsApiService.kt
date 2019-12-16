package com.newsapp.network

import com.newsapp.feature.shared.model.News
import com.newsapp.utils.constants.NewsApiContants
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET(NewsApiContants.api)
    fun getArticles(@Query("sources") sources: String, @Query("apiKey") apiKey: String): Single<Response<News>>
}