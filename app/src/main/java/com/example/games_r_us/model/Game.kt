package com.example.games_r_us.model

import com.google.gson.annotations.SerializedName

data class Game(
    val id: Int,
    val title: String,
    val thumbnail: String,
    @SerializedName("short_description")
    val description: String,
    @SerializedName("game_url")
    val gameUrl: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val developer: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("freetogame_profile_url")
    val profileUrl: String
)