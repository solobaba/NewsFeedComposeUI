package com.solomon.newsappcompose.network.repository

import android.util.Log
import androidx.room.withTransaction
import com.google.gson.Gson
import com.solomon.newsappcompose.data.NewsList
import com.solomon.newsappcompose.data.NewsListDatabase
import com.solomon.newsappcompose.di.API_KEY
import com.solomon.newsappcompose.di.COUNTRY_CODE
import com.solomon.newsappcompose.network.api.NewsApi
import com.solomon.newsappcompose.network.api.NewsData
import com.solomon.newsappcompose.util.networkBoundResource
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class NewsRepository(
    private val newsApi: NewsApi,
    private val db: NewsListDatabase
    ) {
    fun getTopHeadlines(countryCode: String): Observable<List<NewsData>> =
        newsApi.getTopHeadlines(countryCode, API_KEY).map { it.articles }

    private val newsDao = db.newsDao()

    fun getNewsTwo() = flow {
        val result = newsApi.getTopHeadlinesTwo(COUNTRY_CODE, API_KEY)

        val c = result.articles.count()
        Log.e("count", c.toString())

        val r = result.articles.toNewsList()

        Log.e("result", Gson().toJson(r))
        newsDao.insertNews(r)
        Log.e("insert", Gson().toJson(r))

        val news = newsDao.getAllNewsTwo()
        emit(news)
    }.onStart {
//        val l = listOf(
//            NewsList(
//                title = "a",
//                date = "2022-05-25T11:00:00Z"
//            ),
//            NewsList(
//                title = "b",
//                date = "2022-05-26T11:00:00Z"
//            )
//        )
//        newsDao.insertNews(l)

        val news = newsDao.getAllNewsTwo()
        emit(news)
    }.catch { e ->
        e.printStackTrace()
    }

    fun getNews() = networkBoundResource(

        // Query to return the list of all news
        query = {
            newsDao.getAllNews()
        },

        // Just for testing purpose,
        // a delay of 2 second is set.
        fetch = {
            delay(2000)
                newsApi.getTopHeadlines(COUNTRY_CODE, API_KEY).map { it.articles }
        },

        // Save the results in the table.
        // If data exists, then delete it
        // and then store.
        saveFetchResult = { NewsList: Observable<List<NewsData>> ->
            db.withTransaction {
                newsDao.deleteAllNews()
                newsDao.insertNews(emptyList())
            }
        }
    )

    private fun List<NewsData>.toNewsList() : List<NewsList> {
        val mutable = mutableListOf<NewsList>()
        for (it in this){
            mutable.add(
                NewsList(
                    author = it.author.orEmpty(),
                    title = it.title.orEmpty(),
                    description = it.description.orEmpty(),
                    imageUrl = it.urlToImage.orEmpty(),
                    content = it.content.orEmpty(),
                    fullArticleUrl = it.url.orEmpty(),
                    date = it.publishedAt.orEmpty()
                )
            )
            Log.e("muta", mutable.toString())
        }
        Log.e("aftermuta", "here")
        return mutable
    }
}