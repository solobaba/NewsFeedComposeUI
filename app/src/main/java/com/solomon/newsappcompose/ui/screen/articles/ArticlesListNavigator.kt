package com.solomon.newsappcompose.ui.screen.articles

import android.content.Context
import com.solomon.newsappcompose.model.Article
import com.solomon.newsappcompose.ui.screen.details.ArticleDetailsActivity

class ArticlesListNavigator(private val context: Context) {

    fun openArticleDetailsScreen(article: Article) {
        context.startActivity(ArticleDetailsActivity.newIntent(context, article))
    }
}