package com.example.games_r_us.account.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.games_r_us.FirebaseUtils.auth
import com.example.games_r_us.databinding.ActivityRegisterBinding
import com.example.games_r_us.model.User
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private val TAG = "RegisterActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            btnFinish.setOnClickListener {
                registerNewUser()
            }

        }
    }

    private fun registerNewUser() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val firstName = binding.etFName.text.toString()
        val lastName = binding.etLName.text.toString()
        val user = User(firstName, lastName, email, password)

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    val intent = Intent(this, SignInActivity::class.java)
                    val bundle = Bundle()
                    bundle.putSerializable(ARG_USER, user)
                    intent.putExtras(bundle)
                    startActivity(intent)
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    companion object {
        const val ARG_USER = "user"
    }
}