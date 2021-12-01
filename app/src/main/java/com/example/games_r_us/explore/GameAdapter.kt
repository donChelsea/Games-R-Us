package com.example.games_r_us.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.games_r_us.databinding.GameItemViewBinding
import com.example.games_r_us.model.Game
import com.squareup.picasso.Picasso

class GameAdapter(private val games: List<Game>): RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        return GameViewHolder(
            GameItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(games[position])
    }

    override fun getItemCount() = games.size

    inner class GameViewHolder(private val binding: GameItemViewBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(game: Game) {
            binding.apply {
                gameTitle.text = game.title
                Picasso.get().load(game.thumbnail).into(gameImage)
            }
        }
    }
}