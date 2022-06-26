package com.solomon.newsappcompose.di

import com.solomon.newsappcompose.ui.screen.details.ArticleDetailsNavigator
import com.solomon.newsappcompose.ui.screen.details.ArticleDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val articleDetailsModule = module {
    factory { ArticleDetailsNavigator(get()) }
    viewModel { ArticleDetailsViewModel(get(), get()) }
}