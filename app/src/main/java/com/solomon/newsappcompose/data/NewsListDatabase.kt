package com.solomon.newsappcompose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.solomon.newsappcompose.data.NewsDao
import com.solomon.newsappcompose.data.NewsList

@Database(entities = [NewsList::class], version = 1)
abstract class NewsListDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}
