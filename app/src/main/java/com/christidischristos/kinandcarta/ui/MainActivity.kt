package com.christidischristos.kinandcarta.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.christidischristos.kinandcarta.R
import com.christidischristos.kinandcarta.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val navController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar(binding)
    }

    private fun setupToolbar(binding: ActivityMainBinding) {
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.toolbar.title = ""
            binding.toolbarTitleTv.text = destination.label
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
