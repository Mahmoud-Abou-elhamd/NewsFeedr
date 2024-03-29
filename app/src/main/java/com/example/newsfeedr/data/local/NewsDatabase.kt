package com.example.newsfeedr.data.local

import androidx.room.Database
import androidx.room.TypeConverters
import com.example.newsfeedr.domain.model.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(NewsTypeConvertor::class)
abstract class NewsDatabase {
    abstract val newsDao: NewsDao
}