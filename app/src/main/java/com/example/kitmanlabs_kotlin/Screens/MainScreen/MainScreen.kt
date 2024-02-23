package com.example.kitmanlabs_kotlin.Screens.MainScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.kitmanlabs_kotlin.Screens.SharedViews.ErrorState
import com.example.kitmanlabs_kotlin.Screens.SharedViews.LoadingState

@Composable
fun MainScreen(name: String, viewModel: MainScreenViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (viewModel.loadingState) {
            is MainScreenLoadingState.Error -> ErrorState { viewModel.fetchData() }
            MainScreenLoadingState.Loading -> LoadingState()
            MainScreenLoadingState.Idle -> {
                viewModel.fetchData()
                LoadingState()
            }

            is MainScreenLoadingState.Success -> LoadedState(
                (viewModel.loadingState as MainScreenLoadingState.Success).data, name
            )
        }
    }
}

@Composable
fun LoadedState(data: MainScreenViewModel.PresentableData, name: String) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        item {
            Text(
                "Welcome, $name", fontStyle = FontStyle.Italic, fontSize = 32.sp
            )
        }
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
    val viewModel = MainScreenViewModel()
    viewModel.loadingState = MainScreenLoadingState.Success(viewModel.mockPresentableData)
    MainScreen("Test name", viewModel)
}