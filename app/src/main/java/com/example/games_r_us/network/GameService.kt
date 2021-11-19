package com.example.games_r_us.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object GameService {
    private const val BASE_URL = "https://www.freetogame.com/"

    val gameService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GameApi::class.java)


}