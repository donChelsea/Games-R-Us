package com.example.games_r_us.account

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.games_r_us.R
import com.example.games_r_us.account.authentication.SignInActivity
import com.example.games_r_us.databinding.FragmentAccountBinding
import com.google.firebase.auth.FirebaseAuth

class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding
    private lateinit var auth: FirebaseAuth

    private val TAG = "AccountFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        checkIfUserIsLoggedIn()
    }

    private fun checkIfUserIsLoggedIn() {
        if (auth.currentUser != null) {
            binding.apply {
                tvSignUp.text = resources.getString(R.string.signOut)
                tvSignUp.setOnClickListener { signOut() }
            }
        } else {
            binding.tvSignUp.setOnClickListener {
                val intent = Intent(requireContext(), SignInActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun signOut() {
        auth.signOut()
        binding.tvSignUp.text = resources.getString(R.string.signInSignUp)
    }

}