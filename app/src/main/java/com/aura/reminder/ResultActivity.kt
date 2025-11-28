package com.aura.reminder

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aura.reminder.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Enable edge-to-edge display
        enableEdgeToEdge()
        
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupUI()
        startParticleAnimation()
        
        // Auto-finish after 3 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            finish()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }, 3000)
    }
    
    private fun enableEdgeToEdge() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { view, insets ->
            val systemBarsVisible = insets.isVisible
            val systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            
            // Set padding for the root view
            view.setPadding(
                view.paddingStart + systemBarsInsets.left,
                view.paddingTop + systemBarsInsets.top,
                view.paddingEnd + systemBarsInsets.right,
                view.paddingBottom + systemBarsInsets.bottom
            )
            
            insets
        }
    }
    
    private fun setupUI() {
        // Set background gradient
        binding.root.setBackgroundResource(R.drawable.background_gradient)
        
        // Set the result text
        binding.resultText.text = "Остановлено."
        
        // Apply glass morphism effect to text container
        binding.resultContainer.setBackgroundResource(R.drawable.glass_card_background)
    }
    
    private fun startParticleAnimation() {
        // Create particle effect by animating multiple views
        for (i in 0 until 12) {
            createParticleView(i)
        }
    }
    
    private fun createParticleView(index: Int) {
        val particle = View(this)
        particle.setBackgroundResource(R.drawable.particle_background)
        
        val layoutParams = androidx.appcompat.widget.LinearLayoutCompat.LayoutParams(
            12, 12
        )
        particle.layoutParams = layoutParams
        
        // Random starting position
        val startX = (binding.root.width / 2) + (Math.random() * 100 - 50).toInt()
        val startY = (binding.root.height / 2) + (Math.random() * 100 - 50).toInt()
        particle.x = startX.toFloat()
        particle.y = startY.toFloat()
        
        binding.root.addView(particle)
        
        // Animate particle
        val anim = AnimationUtils.loadAnimation(this, R.anim.particle_animation)
        anim.startOffset = (index * 100).toLong()
        
        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            
            override fun onAnimationEnd(animation: Animation?) {
                // Remove particle after animation
                binding.root.removeView(particle)
            }
            
            override fun onAnimationRepeat(animation: Animation?) {}
        })
        
        particle.startAnimation(anim)
    }
}