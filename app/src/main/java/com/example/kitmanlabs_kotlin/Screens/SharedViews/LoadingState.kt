package com.example.kitmanlabs_kotlin.Screens.SharedViews

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun LoadingState(onLoad: (() -> Unit)? = null) {
    LaunchedEffect(Unit) {
        onLoad?.let { it() }
    }
    CircularProgressIndicator(
        modifier = Modifier.size(64.dp),
        color = MaterialTheme.colorScheme.secondary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
}