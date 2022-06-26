package com.solomon.newsappcompose.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

//Data class to store the data
@Parcelize
@Entity(tableName = "news")
data class NewsList(
    @PrimaryKey val title: String = "",
    val author: String = "N/A",
    val description: String = "N/A",
    val imageUrl: String = "N/A",
    val content: String = "N/A",
    val fullArticleUrl: String = "N/A",
    val date: String = "N/A"
): Parcelable