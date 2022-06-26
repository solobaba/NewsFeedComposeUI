package com.solomon.newsappcompose.ui.screen.details

import android.content.Context
import android.net.Uri
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK

class ArticleDetailsNavigator(private val context: Context) {

    fun openInBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            flags = FLAG_ACTIVITY_NEW_TASK
            data = Uri.parse(url)
        }
        context.startActivity(intent)
    }
}