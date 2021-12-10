package com.example.games_r_us.explore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.games_r_us.model.Game
import com.example.games_r_us.model.GameData
import com.example.games_r_us.network.GameService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExploreViewModel : ViewModel() {
    private val _allGames = MutableLiveData<List<Game>>()
    val allGames: LiveData<List<Game>> = _allGames

    private val TAG = "ExploreViewModel"
    private val platformSet = mutableSetOf<String>()
    private val genreSet = mutableSetOf<String>()

    suspend fun getAllGames() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = GameService.gameService.getAllGames()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.body() != null) {
                    _allGames.value = response.body()
                } else {
                    throw Exception(response.message())
                }
            }
        }
    }

    fun getPlatforms(games: List<Game>): List<String> {
        games.forEach { game ->
            if (!platformSet.contains(game.platform)) {
                Log.d(TAG, game.platform)
                platformSet.add(game.platform.trim())
            }
        }
        return platformSet.toList()
    }

    fun getGenres(): List<String> {
        return GameData.genres
    }
}