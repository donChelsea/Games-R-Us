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

        binding.apply {
            tvSignOut.setOnClickListener {
                auth.signOut()
                layoutSignedOut.visibility = View.VISIBLE
                layoutSignedIn.visibility = View.GONE
            }

            btnCreateAccount.setOnClickListener {
                val intent = Intent(requireContext(), SignInActivity::class.java)
                startActivity(intent)
            }
        }

        checkIfUserIsLoggedIn()
    }

    private fun checkIfUserIsLoggedIn() {
        if (auth.currentUser != null) {
            binding.apply {
                layoutSignedIn.visibility = View.VISIBLE
                layoutSignedOut.visibility = View.GONE
            }
        } else {
            binding.apply {
                layoutSignedOut.visibility = View.VISIBLE
                layoutSignedIn.visibility = View.GONE
            }
        }
    }
}