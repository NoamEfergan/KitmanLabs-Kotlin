package com.example.kitmanlabs_kotlin.Screens.MainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.kitmanlabs_kotlin.Models.MainView.Athlete.AthleteItem
import com.example.kitmanlabs_kotlin.Models.MainView.Image
import com.example.kitmanlabs_kotlin.R

@Composable
fun AthleteView(athlete: AthleteItem) {
    Row(
        modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SubcomposeAsyncImage(
            model = athlete.image.url,
            contentDescription = "The image of the athlete ${athlete.first_name} ${athlete.last_name}",
            loading = {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            },
            error = {
                Image(
                    painter = painterResource(R.drawable.person_icon),
                    contentDescription = "Placeholder person icon, loading failed"
                )
            }
        )
        Details(athlete)
    }
}

@Composable
fun Details(athlete: AthleteItem) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.Start,

        ) {
        DetailRow(bold = athlete.first_name, light = athlete.last_name)
        DetailRow(bold = "ID:", light = athlete.id.toString())
        DetailRow(bold = "Username:", light = athlete.username)

    }
}

@Composable
fun DetailRow(bold: String, light: String) {
    Row(
        modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(bold, fontWeight = FontWeight.Light)
        Text(light, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AthleteViewPreview() {
    AthleteView(
        athlete = AthleteItem(
            first_name = "Adam",
            last_name = "Beard",
            id = 1964,
            image = Image(url = "https://kitman.imgix.net/avatar.jpg"),
            username = "abeardathlete",
            squad_ids = listOf(78)
        )
    )
}
