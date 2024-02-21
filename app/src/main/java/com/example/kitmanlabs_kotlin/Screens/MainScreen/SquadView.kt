package com.example.kitmanlabs_kotlin.Screens.MainScreen

import SquadItem
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kitmanlabs_kotlin.Models.MainView.Athlete.AthleteItem
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SquadView(squad: SquadItem, athletes: List<AthleteItem>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text(
                squad.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                fontSize = 32.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
        items(athletes) {
            AthleteView(athlete = it)
        }

    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SquadViewPreview() {
    SquadView(squad = SquadItem.mock.first(), athletes = AthleteItem.mock)
}