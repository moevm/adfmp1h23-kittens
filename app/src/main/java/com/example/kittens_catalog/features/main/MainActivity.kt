package com.example.kittens_catalog.features.main

import android.os.Bundle
import android.os.StrictMode
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.kittens_catalog.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
    }


    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        navController = findNavController(R.id.nav_host_fragment)
        if (viewModel.isAuthenticated.value == false) {
            navigateToAuthFragment()
        }
        return navController.navigateUp()
    }

    private fun navigateToAuthFragment() {
        navController.navigate(R.id.auth_fragment)
    }
}