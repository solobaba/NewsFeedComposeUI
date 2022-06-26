package com.solomon.newsappcompose.ui.screen.articles

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.solomon.newsappcompose.R
import com.solomon.newsappcompose.model.Article
import com.solomon.newsappcompose.model.toModel
import com.solomon.newsappcompose.ui.component.Toolbar
import com.solomon.newsappcompose.ui.component.ErrorView
import com.solomon.newsappcompose.ui.component.LoadingView
import com.solomon.newsappcompose.ui.component.ToolbarIcon

import com.solomon.newsappcompose.ui.screen.articles.ArticlesListIntent.*
import com.solomon.newsappcompose.ui.screen.articles.ArticlesListState.*
import com.solomon.newsappcompose.ui.screen.articles.view.ArticlesList
import com.solomon.newsappcompose.ui.screen.details.ArticleDetailsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArticlesListActivity : AppCompatActivity() {

    private val viewModel: ArticlesListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //postIntent(Fetch)

        setContent {
            MaterialTheme {

                Column {
                    TextHeader()
                    //Toolbar()
                    RenderBody {
                        ArticleDetailsActivity.newIntent(this@ArticlesListActivity, it).apply {
                            startActivity(this)
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun TextHeader() =
        Text(
            text = "Top News",
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
            modifier = (Modifier
                .fillMaxWidth()
                .padding(16.dp)),
            color = Color.White
        )

    @Composable
    private fun RenderBody(
        article: (Article) -> Unit
    ) {
        val viewModel by org.koin.androidx.compose.viewModel<ArticlesListViewModel>()
        val loading by viewModel.loading.collectAsState()
        val data by viewModel.data.collectAsState()

        LaunchedEffect(key1 = Unit) {
            viewModel.getNews()
        }

        when {
            loading -> LoadingView()
            data.first.isNotEmpty() -> ArticlesList(data.first.toModel()) {
                article(it)
            }
            !data.second.isNullOrEmpty() -> ErrorView(data.second!!) {
                //postIntent(Retry)
            }
        }
    }

//    @Composable
//    private fun Toolbar() {
//        Toolbar(
//            title = getString(R.string.app_name),
//            rightIcon = ToolbarIcon(
//                icon = Icons.Default.Refresh,
//                onClick = {
//                    //postIntent(Refresh)
//                }
//            )
//        )
//    }

    private fun postIntent(intent: ArticlesListIntent) {
        //viewModel.processIntent(intent)
    }
}