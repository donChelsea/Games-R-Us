package com.example.games_r_us.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.games_r_us.databinding.GenreItemViewBinding

class GenreAdapter(private val genres: List<String>): RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(
            GenreItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(genres[position])
    }

    override fun getItemCount() = genres.size

    inner class GenreViewHolder(private val binding: GenreItemViewBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(game: String) {
            binding.apply {
                gameGenre.text = game
            }
        }
    }
}