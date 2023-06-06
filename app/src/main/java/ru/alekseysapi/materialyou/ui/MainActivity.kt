package com.geekbrains.materialyou.ui

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AnticipateInterpolator
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import com.geekbrains.materialyou.R
import com.geekbrains.materialyou.databinding.ActivityMainBinding
import com.geekbrains.materialyou.ui.picture.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commitNow()
        }

        var isHideSplashScreen = false

        object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                isHideSplashScreen = true
            }
        }.start()

        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (isHideSplashScreen) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        false
                    }
                }
            })

        splashScreen.setOnExitAnimationListener { splashScreenView ->
            val slideLeft = ObjectAnimator.ofFloat(
                splashScreenView,
                View.TRANSLATION_X,
                0f,
                -splashScreenView.height.toFloat()
            )
            slideLeft.interpolator = AnticipateInterpolator()
            slideLeft.duration = 1000L

            slideLeft.doOnEnd { splashScreenView.remove() }
            slideLeft.start()
        }
    }
}