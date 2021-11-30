package com.example.games_r_us.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.games_r_us.R
import com.example.games_r_us.databinding.FragmentExploreBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import android.view.*
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.games_r_us.model.Game
import kotlin.math.sign

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

        viewModel.allGames.observe(viewLifecycleOwner, Observer { games ->
            val genres = viewModel.getGenres(games)

            binding.apply {
                genreRecyclerview.adapter = GenreAdapter(genres)
                genreRecyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
            }


//            val gameAdapter: ArrayAdapter<String> = ArrayAdapter(requireContext(), R.layout.list_item_view, genres)
//            binding.listview.adapter = gameAdapter
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

        })

        GlobalScope.launch(Dispatchers.Main) {
            viewModel.getAllGames()
        }
    }

}