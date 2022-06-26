package com.solomon.newsappcompose.ui.screen.articles

import com.solomon.newsappcompose.model.Article

sealed class ArticlesListIntent {
    object Fetch : ArticlesListIntent()
    object Refresh : ArticlesListIntent()
    object Retry : ArticlesListIntent()
    class ShowDetails(val article: Article) : ArticlesListIntent()
}