package com.solomon.newsappcompose.di

import com.solomon.newsappcompose.network.api.NewsApi
import com.solomon.newsappcompose.network.repository.NewsRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

private const val API_URL = "https://newsapi.org/v2/"
const val API_KEY = "2d021085c2e64c23927ff485d9f4299b"
const val COUNTRY_CODE = "us"

val networkModule = module {
    factory<Retrofit> {
        val httpLoginInterceptor = HttpLoggingInterceptor()
        httpLoginInterceptor.level = HttpLoggingInterceptor.Level.BODY

        Retrofit.Builder()
            .client(OkHttpClient.Builder().addInterceptor(httpLoginInterceptor).build())
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(API_URL)
            .build()
    }

    factory<NewsApi> { get<Retrofit>().create(NewsApi::class.java) }
    factory { NewsRepository(get(), get()) }
}