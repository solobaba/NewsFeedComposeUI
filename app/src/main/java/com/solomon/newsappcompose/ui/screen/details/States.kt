package com.solomon.newsappcompose.ui.screen.details

import com.solomon.newsappcompose.model.Article

sealed class ArticleDetailsState {
    object Loading : ArticleDetailsState()
    class Success(val article: Article) : ArticleDetailsState()
}