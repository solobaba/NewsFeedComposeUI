package com.solomon.newsappcompose.ui

import android.app.Application
import com.solomon.newsappcompose.di.articleDetailsModule
import com.solomon.newsappcompose.di.database
import com.solomon.newsappcompose.di.networkModule
import com.solomon.newsappcompose.di.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NewsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(org.koin.core.logger.Level.ERROR)
            androidContext(this@NewsApp)
            modules(networkModule, articleDetailsModule, database, viewModel
            )
        }
    }
}