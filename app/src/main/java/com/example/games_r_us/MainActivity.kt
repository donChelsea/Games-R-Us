package com.example.games_r_us

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.games_r_us.model.Game
import com.example.games_r_us.network.GameService.gameService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.IO) {
            val response = gameService.getAllGames()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.body() != null) {
                    Log.d(TAG, "games: ${response.body()?.size}")
                } else {
                    throw Exception(response.message())
                }
            }
        }

    }


}