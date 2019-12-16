package com.newsapp.feature.shared.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class News(val articles: List<Articles>)

@Entity(tableName = "articles")
data class Articles(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "title")
    var title: String? = null,
    @ColumnInfo(name = "description")
    var description: String? = null,
    @ColumnInfo(name = "urlToImage")
    var urlToImage: String? = null ,
    @ColumnInfo(name = "url")
    var url: String? = null
)