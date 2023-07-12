package com.vasilets.navigationcomponenttest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vasilets.navigationcomponenttest.BottomNavConfigurator.handleBottomNavVisibility
import com.vasilets.navigationcomponenttest.BottomNavConfigurator.setBottomNav
import com.vasilets.navigationcomponenttest.BottomNavConfigurator.setUpNewContentItem
import com.vasilets.navigationcomponenttest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)


        navView.setBottomNav(navController)
        navView.setUpNewContentItem()
        navView.handleBottomNavVisibility(navController)
    }
}