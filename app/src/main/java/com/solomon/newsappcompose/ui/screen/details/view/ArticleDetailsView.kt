package com.solomon.newsappcompose.ui.screen.details.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.solomon.newsappcompose.model.Article
import com.solomon.newsappcompose.ui.component.Image

@Composable
fun ArticleDetailsView(article: Article, onReadAllClick: (articleUrl: String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(article.imageUrl,
            Modifier
                .fillMaxWidth()
                .height(400.dp))
        ArticleDescription(article.description)
        ArticleContent(article.content)
        ReadAllButton { onReadAllClick.invoke(article.fullArticleUrl) }
    }
}

@Composable
private fun ArticleDescription(description: String) {
    Text(
        text = description,
        color = Color.White,
        style = MaterialTheme.typography.h6,
        modifier = Modifier.padding(all = 16.dp)
    )
}

@Composable
private fun ArticleContent(content: String) {
    Text(
        text = content,
        color = Color.White,
        style = MaterialTheme.typography.body1,
        modifier = Modifier.padding(all = 16.dp)
    )
}

@Composable
private fun ReadAllButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        content = { Text(text = "Read full news", color = Color.Black) },
        modifier = Modifier.padding(all = 16.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
    )
}