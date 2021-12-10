package com.example.games_r_us.explore.game_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.games_r_us.Utils
import com.example.games_r_us.model.GameDetail
import com.example.games_r_us.network.GameService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameDetailViewModel: ViewModel() {
    private val _game = MutableLiveData<GameDetail>()
    val game: LiveData<GameDetail> = _game

    var gameId: String = ""

    suspend fun getGameById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = GameService.gameService.getGameById(id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.body() != null) {
                    _game.value = response.body()
                } else {
                    throw Exception(response.message())
                }
            }
        }
    }

    fun getPlatformIcon(platform: String): Int {
       return Utils.getPlatformIcon(platform)
    }

}