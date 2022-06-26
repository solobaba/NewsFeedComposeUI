package com.solomon.newsappcompose.model

import android.os.Parcelable
import com.solomon.newsappcompose.data.NewsList

import com.solomon.newsappcompose.network.api.NewsData
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val author: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val content: String,
    val fullArticleUrl: String
) : Parcelable

fun NewsData.toModel(): Article =
    Article(
        author = author.orEmpty(),
        title = title,
        description = description.orEmpty(),
        imageUrl = urlToImage.orEmpty(),
        content = content.orEmpty(),
        fullArticleUrl = url
    )

fun List<NewsList>.toModel(): List<Article> =
    map{
        Article(
            author = it.author.orEmpty(),
            title = it.title,
            description = it.description.orEmpty(),
            imageUrl = it.imageUrl.orEmpty(),
            content = it.content.orEmpty(),
            fullArticleUrl = it.fullArticleUrl
        )
    }