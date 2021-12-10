package com.example.games_r_us.model

import com.google.gson.annotations.SerializedName

data class Game(
    val id: Int,
    val title: String,
    val thumbnail: String,
    @SerializedName("short_description")
    val descriptionShort: String,
    @SerializedName("description")
    val descriptionLong: String,
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

data class GameDetail(
    val id: Int,
    val title: String,
    val thumbnail: String,
    @SerializedName("short_description")
    val descriptionShort: String,
    @SerializedName("description")
    val descriptionLong: String,
    @SerializedName("game_url")
    val gameUrl: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val developer: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("freetogame_profile_url")
    val profileUrl: String,
    @SerializedName("minimum_system_requirements")
    val systemRequirements: SystemRequirements,
    val screenshots: List<Screenshot>,
)

data class SystemRequirements(
    val os: String,
    val processor: String,
    val memory: String,
    val graphics: String,
    val storage: String
)

data class Screenshot(
    val id: Int,
    val image: String
)
