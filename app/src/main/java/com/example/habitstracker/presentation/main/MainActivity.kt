package com.example.habitstracker.presentation.main

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.habitstracker.R
import com.example.habitstracker.databinding.ActivityMainBinding
import com.example.habitstracker.presentation.home.habiteditor.HabitEditorFragment

class MainActivity : AppCompatActivity(), HabitEditorFragment.SupportActionBarHelper {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController
    private lateinit var appBarConfig: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.fragment)
        appBarConfig = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.appInfoFragment),
            binding.drawerLayout
        )
        binding.navigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfig)

        setupProfileImage()
    }

    private fun setupProfileImage() {
        val headerView = binding.navigationView.getHeaderView(0)
        val profileImage = headerView.findViewById<ImageView>(R.id.profileImageView)

        Glide
            .with(this)
            .load(PROFILE_IMAGE_URL)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.drawable.ic_base_error)
            .fitCenter()
            .into(profileImage)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfig) || super.onSupportNavigateUp()
    }

    override fun changeSupportActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    companion object {

        private const val PROFILE_IMAGE_URL = "https://yar-citi-dog.ru/wp-content/uploads/siba-inu-foto26.jpg"

    }

}