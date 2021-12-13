package com.example.games_r_us.account.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.games_r_us.MainActivity
import com.example.games_r_us.account.authentication.RegisterActivity.Companion.ARG_USER
import com.example.games_r_us.databinding.ActivitySignInBinding
import com.example.games_r_us.model.User
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.auth.UserProfileChangeRequest

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseUser

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var auth: FirebaseAuth

    private val TAG = "SignInActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.apply {
            tvRegister.setOnClickListener {
                val intent = Intent(this@SignInActivity, RegisterActivity::class.java)
                startActivity(intent)
            }

            btnSignIn.setOnClickListener {
                signInUser()
            }
        }
    }

    private fun signInUser() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    updateUserProfile()
                    val intent = Intent(this@SignInActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateUserProfile() {
        if (intent.extras != null) {
            val bundle = intent.extras
            val userInfo = bundle?.get(ARG_USER) as User
            val user = auth.currentUser

            val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName("${userInfo.firstName} ${userInfo.lastName}")
                .build()

            user?.updateProfile(profileUpdates)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "User profile updated.")
                    }
                }
        }
    }

}