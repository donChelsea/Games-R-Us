package com.example.games_r_us.explore.game_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.games_r_us.databinding.ScreenshotItemViewBinding
import com.example.games_r_us.model.Screenshot
import com.squareup.picasso.Picasso

class ScreenshotAdapter(private val screenshots: List<Screenshot>) : RecyclerView.Adapter<ScreenshotAdapter.ScreenshotViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScreenshotViewHolder {
        return ScreenshotViewHolder(
            ScreenshotItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ScreenshotAdapter.ScreenshotViewHolder, position: Int) {
        holder.bind(screenshots[position])
    }

    override fun getItemCount() = screenshots.size

    inner class ScreenshotViewHolder(private val binding: ScreenshotItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(screenshot: Screenshot) {
            Picasso.get().load(screenshot.image).into(binding.imageView)
        }
    }


}
