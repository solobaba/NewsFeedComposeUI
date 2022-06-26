package com.solomon.newsappcompose.network.api

data class NewsResponse(
    val articles: List<NewsData>,
    val status: String,
    val totalResults: Int
)

data class NewsData(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)

data class Source(
    val id: Any,
    val name: String
)