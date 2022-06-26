package com.solomon.newsappcompose.di

import androidx.room.Room
import com.solomon.newsappcompose.data.NewsListDatabase
import com.solomon.newsappcompose.ui.screen.articles.ArticlesListViewModel
import com.solomon.newsappcompose.usecase.GetArticlesUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val database = module {
    single {
        Room.databaseBuilder(androidApplication(), NewsListDatabase::class.java, "NewsList.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        GetArticlesUseCase(get())
    }
}

val viewModel = module {
    viewModel{
        ArticlesListViewModel(get())
    }
}