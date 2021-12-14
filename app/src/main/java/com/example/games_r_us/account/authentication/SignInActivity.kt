package com.example.games_r_us.account.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.games_r_us.FirebaseUtils.auth
import com.example.games_r_us.FirebaseUtils.database
import com.example.games_r_us.MainActivity
import com.example.games_r_us.account.authentication.RegisterActivity.Companion.ARG_USER
import com.example.games_r_us.databinding.ActivitySignInBinding
import com.example.games_r_us.model.User
import com.example.games_r_us.model.UserData

import com.google.firebase.auth.UserProfileChangeRequest

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding

    private val TAG = "SignInActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                    createUserInDatabase()
                    val intent = Intent(this@SignInActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun createUserInDatabase() {
        if (intent.extras != null) {
            val bundle = intent.extras
            val userInfo = bundle?.get(ARG_USER) as User
            val user = auth.currentUser

            user?.let { writeNewUser(it.uid) }

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

    private fun getUserData() {}

    fun writeNewUser(userId: String) {
        val userData = UserData(userId, listOf(), listOf(), listOf())

        database.reference.child("users").child(userId).setValue(userData)
    }
}