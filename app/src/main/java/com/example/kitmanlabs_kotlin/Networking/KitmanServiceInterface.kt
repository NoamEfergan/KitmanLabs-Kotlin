package com.example.kitmanlabs_kotlin.Networking

import SquadItem
import com.example.kitmanlabs_kotlin.Constants.Constants
import com.example.kitmanlabs_kotlin.Models.Login.LoginRequest
import com.example.kitmanlabs_kotlin.Models.Login.LoginResponse
import com.example.kitmanlabs_kotlin.Models.MainView.Athlete.AthleteItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface KitmanServiceInterface {
    @POST(Constants.login)
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET(Constants.athletes)
    suspend fun getAthletes(): Response<List<AthleteItem>>

    @GET(Constants.squad)
    suspend fun getSquads(): Response<List<SquadItem>>
}