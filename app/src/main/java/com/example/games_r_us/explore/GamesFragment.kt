package com.example.games_r_us.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.games_r_us.databinding.FragmentGamesBinding

class GamesFragment : Fragment() {
    private lateinit var binding: FragmentGamesBinding
    private val viewModel: ExploreViewModel by viewModels()
    private var genre: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) genre = requireArguments().getString(ARG_GENRE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGamesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val genre = arguments?.getString(ARG_GENRE)

        Toast.makeText(requireContext(), genre, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val ARG_GENRE = "genre"

        fun newInstance(genre: String): GamesFragment {
            val fragment = GamesFragment()
            val args = Bundle()
            args.putString(ARG_GENRE, genre)
            fragment.arguments = args
            return fragment
        }
    }

}