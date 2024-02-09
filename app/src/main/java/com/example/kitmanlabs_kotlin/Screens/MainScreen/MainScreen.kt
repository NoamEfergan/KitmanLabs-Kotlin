package com.example.kitmanlabs_kotlin.Screens.MainScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.kitmanlabs_kotlin.Screens.SharedViews.ErrorState
import com.example.kitmanlabs_kotlin.Screens.SharedViews.LoadingState

@Composable
fun MainScreen() {
    val viewModel by remember { mutableStateOf(MainScreenViewModel()) }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (viewModel.loadingState) {
            is MainScreenLoadingState.Error -> ErrorState { viewModel.fetchData() }
            MainScreenLoadingState.Loading -> LoadingState()
            MainScreenLoadingState.Idle -> {
                LoadingState()
            }
            is MainScreenLoadingState.Success -> LoadedState(
                (viewModel.loadingState as MainScreenLoadingState.Success).data
            )
        }
    }
}

@Composable
fun LoadedState(data: MainScreenViewModel.PresentableData) {
    LazyColumn {
        items(data.keys.toList()) { squad ->
            data[squad]?.let { athletes ->
                SquadView(squad = squad, athletes = athletes)
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}