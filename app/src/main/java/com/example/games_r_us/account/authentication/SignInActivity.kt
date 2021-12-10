package com.example.games_r_us.account.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.games_r_us.R
import com.example.games_r_us.databinding.ActivitySignInBinding
import com.example.games_r_us.explore.gamesbygenre.GamesByGenreFragment

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.auth_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.auth_nav_host_fragment, SignInFragment())
            commit()
        }
    }
}