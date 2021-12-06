package com.example.games_r_us.explore.gamesbygenre

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.games_r_us.R
import com.example.games_r_us.databinding.FragmentGamesByGenreBinding
import com.example.games_r_us.explore.game_detail.GameDetailFragment
import com.example.games_r_us.model.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GamesByGenreFragment : Fragment() {
    private lateinit var binding: FragmentGamesByGenreBinding
    private val viewModel: GamesByGenreViewModel by viewModels()

    private val TAG = "GamesByGenreFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) viewModel.genre = requireArguments().getString(ARG_GENRE).toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGamesByGenreBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GlobalScope.launch(Dispatchers.Main) {
            viewModel.getGamesInGenre(viewModel.genre)
        }

        Toast.makeText(requireContext(), viewModel.genre, Toast.LENGTH_SHORT).show()

        viewModel.gamesInGenre.observe(viewLifecycleOwner, Observer { games ->
            Log.d(TAG, "games: ${games.size}")

            binding.gameGenreRecyclerview.adapter = GameGenreAdapter(games, { game -> onGameClicked(game) })
        })
    }

    private fun onGameClicked(game: Game) {
        val gameDetailFragment = GameDetailFragment.newInstance(game.id.toString())
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, gameDetailFragment)
            .addToBackStack("game_detail")
            .commit()
    }

    companion object {
        const val ARG_GENRE = "genre"

        fun newInstance(genre: String): GamesByGenreFragment {
            val fragment = GamesByGenreFragment()
            val args = Bundle()
            args.putString(ARG_GENRE, genre)
            fragment.arguments = args
            return fragment
        }
    }

}