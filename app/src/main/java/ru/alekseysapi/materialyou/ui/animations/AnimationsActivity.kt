package com.geekbrains.materialyou.ui.animations

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.materialyou.databinding.ActivityAnimationsFabScrollBinding

private const val rotation = "rotation"
private const val translationY = "translationY"
private const val rotationFrom = 0f
private const val rotationTo = 225f
private const val translationOneContainer = -130f
private const val translationTwoContainer = -250f
private const val translationPlusImageFrom = 0f
private const val translationPlusImageTo = -180f
private const val translationZero = 0f

class AnimationsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimationsFabScrollBinding
    private var isExpanded = false

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationsFabScrollBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFAB()
        binding.scrollView.setOnScrollChangeListener { _, _, _, _, _ ->
            binding.toolbar.isSelected = binding.scrollView.canScrollVertically(-1)
        }
    }

    private fun setFAB() {
        setInitialState()

        binding.fab.setOnClickListener {
            if (isExpanded) {
                collapseFab()
            } else {
                expandFAB()
            }
        }
    }

    private fun setInitialState() {
        binding.transparentBackground.apply {
            alpha = 0f
        }
        binding.optionTwoContainer.apply {
            alpha = 0f
            isClickable = false
        }
        binding.optionOneContainer.apply {
            alpha = 0f
            isClickable = false
        }
    }

    private fun expandFAB() {
        isExpanded = true
        ObjectAnimator.ofFloat(binding.plusImageview, rotation, rotationFrom, rotationTo).start()
        ObjectAnimator.ofFloat(binding.optionTwoContainer, translationY, translationTwoContainer).start()
        ObjectAnimator.ofFloat(binding.optionOneContainer, translationY, translationOneContainer).start()

        binding.optionTwoContainer.animate()
            .alpha(1f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.optionTwoContainer.isClickable = true
                    binding.optionTwoContainer.setOnClickListener {
                        Toast.makeText(this@AnimationsActivity, "Option 2", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        binding.optionOneContainer.animate()
            .alpha(1f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.optionOneContainer.isClickable = true
                    binding.optionOneContainer.setOnClickListener {
                        Toast.makeText(this@AnimationsActivity, "Option 1", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        binding.transparentBackground.animate()
            .alpha(0.9f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.transparentBackground.isClickable = true
                }
            })
    }

    private fun collapseFab() {
        isExpanded = false

        ObjectAnimator.ofFloat(binding.plusImageview, rotation, translationPlusImageFrom, translationPlusImageTo).start()
        ObjectAnimator.ofFloat(binding.optionTwoContainer, translationY, translationZero).start()
        ObjectAnimator.ofFloat(binding.optionOneContainer, translationY, translationZero).start()

        binding.optionTwoContainer.animate()
            .alpha(0f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.optionTwoContainer.isClickable = false
                    binding.optionOneContainer.setOnClickListener(null)
                }
            })
        binding.optionOneContainer.animate()
            .alpha(0f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.optionOneContainer.isClickable = false
                }
            })
        binding.transparentBackground.animate()
            .alpha(0f)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.transparentBackground.isClickable = false
                }
            })
    }
}