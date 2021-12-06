package com.example.games_r_us.explore.gamesbygenre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.games_r_us.databinding.GameGenreItemViewBinding
import com.example.games_r_us.model.Game
import com.squareup.picasso.Picasso

class GameGenreAdapter(private val games: List<Game>, private val clickListener: (Game) -> Unit): RecyclerView.Adapter<GameGenreAdapter.GameGenreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameGenreViewHolder {
        return GameGenreViewHolder(
            GameGenreItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GameGenreViewHolder, position: Int) {
        holder.bind(games[position])
    }

    override fun getItemCount() = games.size

    inner class GameGenreViewHolder(private val binding: GameGenreItemViewBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(game: Game) {
            itemView.setOnClickListener { clickListener(game) }

            binding.apply {
                gameTitle.text = game.title
                Picasso.get().load(game.thumbnail).into(gameImage)
            }
        }
    }
}