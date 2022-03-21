package com.example.kotlin1lesson2.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.kotlin1lesson2.R
import com.example.kotlin1lesson2.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigation()
        changeTitleToolbar()
    }

    private fun initBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigation.itemIconTintList
        setupWithNavController(binding.bottomNavigation, navController)
    }

    private fun changeTitleToolbar() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.charactersFragment -> {
                    binding.toolbarMain.title = getString(R.string.characters)
                }
                R.id.episodesFragment -> {
                    binding.toolbarMain.title = getString(R.string.episodes)
                }
                R.id.locationsFragment -> {
                    binding.toolbarMain.title = getString(R.string.locations)
                }
            }
        }
    }
}
