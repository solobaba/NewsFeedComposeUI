package com.solomon.newsappcompose.ui.screen.details

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import com.solomon.newsappcompose.R
import com.solomon.newsappcompose.model.Article
import com.solomon.newsappcompose.ui.component.Toolbar
import com.solomon.newsappcompose.ui.component.ToolbarIcon
import com.solomon.newsappcompose.ui.screen.details.view.ArticleDetailsView

class ArticleDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val article = extractArticle()

        setContent {
            Column {
                Toolbar()
                RenderBody(article) {
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        flags = FLAG_ACTIVITY_NEW_TASK
                        data = Uri.parse(it)
                    }
                    startActivity(intent)
                }
            }
        }
    }

    @Composable
    private fun RenderBody(state: Article, newsUrl: (String) -> Unit) {
        ArticleDetailsView(state) { newsUrl(it) }
    }

    @Composable
    private fun Toolbar() {
        Toolbar(
            title = getString(R.string.article_details),
            navigationIcon = ToolbarIcon(Icons.Default.ArrowBack) { onBackPressed() }
        )
    }

    private fun extractArticle(): Article {
        return intent.extras?.getParcelable(EXTRA_ARTICLE)
            ?: throw IllegalStateException("Extra article can't be null")
    }

    companion object {
        private const val EXTRA_ARTICLE = "EXTRA_ARTICLE"

        fun newIntent(context: Context, article: Article) =
            Intent(context, ArticleDetailsActivity::class.java).apply {
                flags = FLAG_ACTIVITY_NEW_TASK
                putExtra(EXTRA_ARTICLE, article)
            }
    }
}