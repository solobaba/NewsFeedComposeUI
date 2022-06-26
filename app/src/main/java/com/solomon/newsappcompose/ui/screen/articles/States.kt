package com.solomon.newsappcompose.ui.screen.articles

import com.solomon.newsappcompose.model.Article

sealed class ArticlesListState {
    object InitState : ArticlesListState()
    object Loading : ArticlesListState()
    class Error(val errorMessage: String) : ArticlesListState()
    class Success(val articles: List<Article>) : ArticlesListState()
}