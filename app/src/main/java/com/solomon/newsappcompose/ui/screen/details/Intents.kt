package com.solomon.newsappcompose.ui.screen.details

import com.solomon.newsappcompose.model.Article

sealed class ArticleDetailsIntent {
    class Show(val article: Article) : ArticleDetailsIntent()
    class OpenInBrowser(val articleUrl: String) : ArticleDetailsIntent()
}