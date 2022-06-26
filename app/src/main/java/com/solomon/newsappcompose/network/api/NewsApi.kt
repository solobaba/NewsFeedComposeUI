package com.solomon.newsappcompose.network.api

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("country") countryCode: String,
        @Query("apiKey") apiKey: String
    ) : Observable<NewsResponse>

    @GET("top-headlines")
    suspend fun getTopHeadlinesTwo(
        @Query("country") countryCode: String,
        @Query("apiKey") apiKey: String
    ) : NewsResponse
}