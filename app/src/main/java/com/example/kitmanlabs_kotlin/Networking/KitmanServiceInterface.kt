package com.example.kitmanlabs_kotlin.Networking

import com.example.kitmanlabs_kotlin.Constants.Constants
import com.example.kitmanlabs_kotlin.Models.Login.LoginRequest
import com.example.kitmanlabs_kotlin.Models.Login.LoginResponse
import com.example.kitmanlabs_kotlin.Models.MainView.Athlete.AthleteResponse
import com.example.kitmanlabs_kotlin.Models.MainView.Squad.SquadResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface KitmanServiceInterface {
    @POST(Constants.login)
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET(Constants.athletes)
    suspend fun getAthletes(): Response<AthleteResponse>

    @GET(Constants.squad)
    suspend fun getSquads(): Response<SquadResponse>
}