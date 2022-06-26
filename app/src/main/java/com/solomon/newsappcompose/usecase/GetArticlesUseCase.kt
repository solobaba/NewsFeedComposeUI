package com.solomon.newsappcompose.usecase

import com.solomon.newsappcompose.network.repository.NewsRepository
import com.solomon.newsappcompose.model.Article
import com.solomon.newsappcompose.model.toModel
import com.solomon.newsappcompose.network.api.NewsData
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class GetArticlesUseCase(private val newsRepository: NewsRepository) {
    fun get(): Single<List<Article>> =
        newsRepository
            .getTopHeadlines(countryCode = "us")
            .flatMap { Observable.fromIterable(it) }
            .filter(::filterInvalidArticles)
            .map { it.toModel() }
            .toList()

    private fun filterInvalidArticles(newsData: NewsData): Boolean = with(newsData) {
        return author != null && urlToImage != null && description != null && content != null
    }

    fun getNews() = newsRepository.getNewsTwo()
}