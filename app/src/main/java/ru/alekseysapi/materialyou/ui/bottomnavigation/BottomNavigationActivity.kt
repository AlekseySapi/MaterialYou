package com.geekbrains.materialyou.ui.bottomnavigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.materialyou.R
import com.geekbrains.materialyou.databinding.ActivityBottomNavigationViewBinding
import com.geekbrains.materialyou.ui.viewpager.EarthFragment
import com.geekbrains.materialyou.ui.viewpager.MarsFragment
import com.geekbrains.materialyou.ui.viewpager.WeatherFragment

class BottomNavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavigationViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_view_earth -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.bottom_navigation_container, EarthFragment())
                        .commitAllowingStateLoss()
                    true
                }
                R.id.bottom_view_mars -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.bottom_navigation_container, MarsFragment())
                        .commitAllowingStateLoss()
                    true
                }
                R.id.bottom_view_weather -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.bottom_navigation_container, WeatherFragment())
                        .commitAllowingStateLoss()
                    true
                }
                else -> false
            }
        }

        binding.bottomNavigationView.setOnItemReselectedListener { item ->
            when (item.itemId) {
                R.id.bottom_view_earth -> {
                    //Item tapped
                }
                R.id.bottom_view_mars -> {
                    //Item tapped
                }
                R.id.bottom_view_weather -> {
                    //Item tapped
                }
            }
        }

        binding.bottomNavigationView.selectedItemId = R.id.bottom_view_mars

        binding.bottomNavigationView.getOrCreateBadge(R.id.bottom_view_mars)
        val badge = binding.bottomNavigationView.getOrCreateBadge(R.id.bottom_view_earth)
        badge.maxCharacterCount = 2
        badge.number = 999
    }
}