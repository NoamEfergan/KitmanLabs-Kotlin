package com.example.kitmanlabs_kotlin.Screens.SharedViews

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ErrorState(callback: (() -> Unit)) {
    Text(text = "Whoops! something went wrong. Please try again!")
    Button(onClick = callback) {
        Text(text = "Retry")
    }

}