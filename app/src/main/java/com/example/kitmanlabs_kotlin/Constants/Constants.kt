package com.example.kitmanlabs_kotlin.Constants


data class Paths(
    val squad: String = "/squads", val athletes: String = "/athletes"
)

object Constants {
    const val baseUrl: String = "https://kml-tech-test.glitch.me"
    const val login: String = "/session"
    const val squad: String = "/squads"
    const val athletes: String = "/athletes"

}
