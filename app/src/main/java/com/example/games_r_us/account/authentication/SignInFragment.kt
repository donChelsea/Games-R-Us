package com.example.games_r_us.account.authentication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.games_r_us.R
import com.example.games_r_us.databinding.FragmentSignInBinding
import com.example.games_r_us.explore.gamesbygenre.GamesByGenreFragment

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            tvRegister.setOnClickListener {
                val registerFragment = RegisterFragment.newInstance()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.auth_nav_host_fragment, registerFragment)
                    .addToBackStack("sign_in")
                    .commit()
            }
        }

    }

}