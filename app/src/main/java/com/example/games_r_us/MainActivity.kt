package com.example.games_r_us

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.games_r_us.databinding.ActivityMainBinding
import com.example.games_r_us.explore.ExploreFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private val homeFragment = HomeFragment()
    private val exploreFragment = ExploreFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)

        setCurrentFragment(exploreFragment)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> setCurrentFragment(homeFragment)
                R.id.exploreFragment -> setCurrentFragment(exploreFragment)
//                R.id.accountFragment -> setCurrentFragment(settingsFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment, fragment)
            addToBackStack("main")
            commit()
        }

}