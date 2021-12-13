package com.example.games_r_us.account.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.games_r_us.MainActivity
import com.example.games_r_us.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

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
                    val intent = Intent(this@SignInActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
            }

    }

}