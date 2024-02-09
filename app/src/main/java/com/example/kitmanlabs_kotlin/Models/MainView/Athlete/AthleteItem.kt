package com.example.kitmanlabs_kotlin.Models.MainView.Athlete

import com.example.kitmanlabs_kotlin.Models.MainView.Image
import kotlinx.serialization.Serializable

@Serializable
data class AthleteItem(
    val first_name: String,
    val id: Int,
    val image: Image,
    val last_name: String,
    val squad_ids: List<Int>,
    val username: String
) {
    companion object {
        val mock = listOf(
            AthleteItem(
                first_name = "Adam",
                last_name = "Beard",
                id = 1964,
                image = Image(url = "https://kitman.imgix.net/avatar.jpg"),
                username = "abeardathlete",
                squad_ids = listOf(78)
            ),
            AthleteItem(
                first_name = "Al",
                last_name = "Saunders",
                id = 5011,
                image = Image(url = "https://kitman.imgix.net/avatar.jpg"),
                username = "asaunders",
                squad_ids = listOf(78)
            ),
            AthleteItem(
                first_name = "Alvin",
                last_name = "Bailey",
                id = 5932,
                image = Image(url = "https://kitman.imgix.net/avatar.jpg"),
                username = "abailey",
                squad_ids = listOf(72, 185, 190)
            ),
            AthleteItem(
                first_name = "Andre",
                last_name = "Tucker",
                id = 5030,
                image = Image(url = "https://kitman.imgix.net/avatar.jpg"),
                username = "atucker1",
                squad_ids = listOf(78)
            ),
            AthleteItem(
                first_name = "Andrew",
                last_name = "Hawkins",
                id = 2175,
                image = Image(url = "https://kitman.imgix.net/avatar.jpg"),
                username = "ahawkins",
                squad_ids = listOf(72, 185, 189)
            ),
            AthleteItem(
                first_name = "Andy",
                last_name = "Lee",
                id = 2186,
                image = Image(url = "https://kitman.imgix.net/avatar.jpg"),
                username = "alee",
                squad_ids = listOf(72, 191)
            ),
            AthleteItem(
                first_name = "Anthony",
                last_name = "Trem",
                id = 5032,
                image = Image(url = "https://kitman.imgix.net/avatar.jpg"),
                username = "atrem1",
                squad_ids = listOf(78)
            ),
        )
    }

}