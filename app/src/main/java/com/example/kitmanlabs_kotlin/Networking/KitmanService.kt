package com.example.kitmanlabs_kotlin.Networking

import com.example.kitmanlabs_kotlin.Constants.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KitmanService {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val instance: KitmanServiceInterface by lazy {
        retrofit.create(KitmanServiceInterface::class.java)
    }
}