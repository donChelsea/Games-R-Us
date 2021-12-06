package com.example.games_r_us.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.games_r_us.databinding.FragmentExploreBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import android.view.*
import androidx.recyclerview.widget.GridLayoutManager
import com.example.games_r_us.R
import com.example.games_r_us.explore.gamesbygenre.GamesByGenreFragment

class ExploreFragment : Fragment() {
    private lateinit var binding: FragmentExploreBinding
    private val viewModel: ExploreViewModel by viewModels()

    private val TAG = "ExploreFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExploreBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val genres = viewModel.getGenres()

        binding.apply {
            genreRecyclerview.adapter = GenreAdapter(genres, { genre -> onGenreClicked(genre) })
            genreRecyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
        }

//            binding.searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
//                androidx.appcompat.widget.SearchView.OnQueryTextListener {
//                override fun onQueryTextSubmit(p0: String?): Boolean {
//                    binding.searchview.clearFocus()
//                    if (genres.contains(p0)) {
//                        gameAdapter.filter.filter(p0)
//                    }
//                    return false
//                }
//
//                override fun onQueryTextChange(newText: String?): Boolean {
//                    gameAdapter.filter.filter(newText)
//                    return false
//                }
//            })
    }

    private fun onGenreClicked(genre: String) {
        val gamesFragment = GamesByGenreFragment.newInstance(genre)
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, gamesFragment)
            .addToBackStack("games")
            .commit()
    }

}