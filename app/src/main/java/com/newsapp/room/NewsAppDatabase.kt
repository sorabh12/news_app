package com.newsapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.newsapp.feature.shared.model.Articles

@Database(entities = [Articles::class], version = 1)
abstract class NewsAppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}