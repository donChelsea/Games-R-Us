package com.example.games_r_us.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.games_r_us.databinding.PlatformItemViewBinding

class PlatformAdapter(private val platforms: List<String>): RecyclerView.Adapter<PlatformAdapter.PlatformViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatformViewHolder {
        return PlatformViewHolder(
            PlatformItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlatformViewHolder, position: Int) {
        holder.bind(platforms[position])
    }

    override fun getItemCount() = platforms.size

    inner class PlatformViewHolder(private val binding: PlatformItemViewBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(platform: String) {
            binding.platformName.text = platform
        }
    }
}