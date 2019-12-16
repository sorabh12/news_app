package com.newsapp.room

import androidx.room.*
import com.newsapp.feature.shared.model.Articles
import io.reactivex.Single

@Dao
interface NewsDao {
    @Query("SELECT * FROM articles")
    fun getAllArticles(): Single<List<Articles>>

    @Insert
    fun insetArticles(articlesData: List<Articles>)
}