package com.example.games_r_us.explore.game_detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.games_r_us.R
import com.example.games_r_us.databinding.FragmentGameDetailBinding
import com.example.games_r_us.databinding.FragmentGamesByGenreBinding
import com.example.games_r_us.explore.gamesbygenre.GamesByGenreFragment
import com.example.games_r_us.explore.gamesbygenre.GamesByGenreViewModel
import com.example.games_r_us.model.Game
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import me.relex.circleindicator.CircleIndicator3

class GameDetailFragment : Fragment() {
    private val viewModel: GameDetailViewModel by viewModels()
    private lateinit var binding: FragmentGameDetailBinding

    private val TAG = "GameDetailFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) viewModel.gameId = requireArguments().getString(ARG_ID).toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(requireContext(), viewModel.gameId, Toast.LENGTH_SHORT).show()

        viewModel.game.observe(viewLifecycleOwner, Observer { game ->
            Log.d(TAG, "game: ${game.title}")

            binding.apply {
                gameScreenshotsViewPager.adapter = ScreenshotAdapter(game.screenshots)
                indicator.setViewPager(gameScreenshotsViewPager)

                Picasso.get().load(game.thumbnail).into(ivThumbnail)
                Picasso.get().load(viewModel.getPlatformIcon(game.platform)).into(ivPlatformIcon)
                tvGameTitle.text = game.title
                tvGameDescription.text = game.descriptionLong
                tvCategory.text = game.genre

            }

        })

        GlobalScope.launch {
            viewModel.getGameById(viewModel.gameId)
        }

    }

    companion object {
        const val ARG_ID = "id"

        fun newInstance(id: String): GameDetailFragment {
            val fragment = GameDetailFragment()
            val args = Bundle()
            args.putString(ARG_ID, id)
            fragment.arguments = args
            return fragment
        }
    }
}