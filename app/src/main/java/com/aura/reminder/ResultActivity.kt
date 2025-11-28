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
        // Create more particles for a better distributed effect
        for (i in 0 until 20) {
            createParticleView(i)
        }
    }
    
    private fun createParticleView(index: Int) {
        val particle = View(this)
        particle.setBackgroundResource(R.drawable.particle_background)
        
        val layoutParams = androidx.appcompat.widget.LinearLayoutCompat.LayoutParams(
            18, 18
        )
        particle.layoutParams = layoutParams
        
        // Distribute particles across the entire screen more evenly
        val screenWidth = binding.root.width
        val screenHeight = binding.root.height
        
        // Use grid-like distribution for better coverage
        val gridCols = 4
        val gridRows = 3
        val padding = 50
        
        val col = index % gridCols
        val row = index / gridCols
        
        val cellWidth = (screenWidth - 2 * padding) / gridCols
        val cellHeight = (screenHeight - 2 * padding) / gridRows
        
        val startX = (padding + col * cellWidth + Math.random() * cellWidth * 0.8).toInt()
        val startY = (padding + row * cellHeight + Math.random() * cellHeight * 0.8).toInt()
        
        particle.x = startX.toFloat()
        particle.y = startY.toFloat()
        
        binding.root.addView(particle)
        
        // Select random animation from available options
        val animationIds = intArrayOf(
            R.anim.particle_animation,
            R.anim.particle_up_left,
            R.anim.particle_down_right,
            R.anim.particle_down_left
        )
        
        val selectedAnim = animationIds[(Math.random() * animationIds.size).toInt()]
        val anim = AnimationUtils.loadAnimation(this, selectedAnim)
        
        // Add random start delay for more natural effect
        anim.startOffset = (index * 200 + Math.random() * 800).toLong()
        
        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            
            override fun onAnimationEnd(animation: Animation?) {
                // Remove particle after animation completes
                binding.root.removeView(particle)
            }
            
            override fun onAnimationRepeat(animation: Animation?) {}
        })
        
        particle.startAnimation(anim)
    }
}