package com.example.games_r_us.network

import com.example.games_r_us.model.Game
import retrofit2.Response
import retrofit2.http.GET

interface GameApi {

    @GET("api/games")
    suspend fun getAllGames(): Response<List<Game>>
}