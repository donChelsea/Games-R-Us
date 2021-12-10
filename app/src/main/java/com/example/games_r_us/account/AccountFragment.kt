package com.example.games_r_us.account

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.games_r_us.account.authentication.SignInActivity
import com.example.games_r_us.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            tvSignUp.setOnClickListener {
                val intent = Intent(requireContext(), SignInActivity::class.java)
                startActivity(intent)
            }
        }


    }

}