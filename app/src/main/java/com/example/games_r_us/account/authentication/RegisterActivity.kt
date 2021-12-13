package com.example.games_r_us.account.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.games_r_us.R
import com.example.games_r_us.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    private val TAG = "RegisterActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.apply {

            btnFinish.setOnClickListener {
                registerNewUser()
            }

        }
    }

    private fun registerNewUser() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    val intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}