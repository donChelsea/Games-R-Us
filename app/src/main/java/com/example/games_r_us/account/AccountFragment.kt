package com.example.games_r_us.account

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.games_r_us.FirebaseUtils.auth
import com.example.games_r_us.FirebaseUtils.database
import com.example.games_r_us.R
import com.example.games_r_us.account.authentication.SignInActivity
import com.example.games_r_us.databinding.FragmentAccountBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import android.widget.Toast

import com.example.games_r_us.MainActivity

import com.google.firebase.database.DatabaseError

import androidx.annotation.NonNull
import com.example.games_r_us.model.UserData


class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding

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

        binding.apply {
            tvFName.text = auth.currentUser?.displayName

            getData()

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

    private fun getData() {
        val uid = auth.uid
        database.getReference("/users/$uid").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue(UserData()::class.java)
                Log.d(TAG, value.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, error.message)
            }
        })
    }
}