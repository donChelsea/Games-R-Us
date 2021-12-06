package com.example.games_r_us.explore.gamesbygenre

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.games_r_us.model.Game
import com.example.games_r_us.network.GameService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GamesByGenreViewModel : ViewModel() {
    private val _gamesInGenre = MutableLiveData<List<Game>>()
    val gamesInGenre: LiveData<List<Game>> = _gamesInGenre

    var genre = ""

    suspend fun getGamesInGenre(genre: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = GameService.gameService.getGamesByGenre(genre)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.body() != null) {
                    _gamesInGenre.value = response.body()
                } else {
                    throw Exception(response.message())
                }
            }
        }
    }

}