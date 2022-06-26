package com.solomon.newsappcompose.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ErrorView(errorMessage: String, onRetryClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(errorMessage, modifier = Modifier.padding(16.dp))
            Button(onClick = onRetryClick) { Text(text = "Retry") }
        }
    }
}

@Preview
@Composable
fun ErrorViewPreview() {
    ErrorView("Something went wrong", {})
}