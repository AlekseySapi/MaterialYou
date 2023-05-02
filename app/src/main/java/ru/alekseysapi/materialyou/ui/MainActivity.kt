package com.geekbrains.materialyou.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.materialyou.R
import com.geekbrains.materialyou.databinding.ActivityMainBinding
import com.geekbrains.materialyou.ui.picture.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //setTheme(R.style.PinkTheme)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commitNow()
        }
    }
}