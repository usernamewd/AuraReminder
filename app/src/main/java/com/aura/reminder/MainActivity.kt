package com.aura.reminder

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.aura.reminder.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var mediaPlayer: MediaPlayer? = null
    private var vibrator: Vibrator? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Enable edge-to-edge display
        enableEdgeToEdge()
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        
        // Set up UI with Material Three design
        setupUI()
        
        // Check if today is a reminder day
        checkReminderDay()
        
        // Set up calendar button click
        binding.calendarButton.setOnClickListener {
            showCalendarSelector()
        }
        
        // Set up reminder buttons
        binding.arriveButton.setOnClickListener {
            startResultActivity("Приехал к бабушке Тане!")
        }
        
        binding.declineButton.setOnClickListener {
            startResultActivity("Не хочу ехать!")
        }
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
        // Apply glass morphism effects
        setupGlassEffect()
        
        // Set up the reminder card with breathing animation
        startBreathingAnimation()
    }
    
    private fun setupGlassEffect() {
        // Apply glass morphism to main card
        binding.reminderCard.setBackgroundResource(R.drawable.glass_card_background)
        
        // Apply blur and transparency effects programmatically
        binding.root.setBackgroundResource(R.drawable.background_gradient)
    }
    
    private fun startBreathingAnimation() {
        binding.reminderCard.animate()
            .scaleX(1.02f)
            .scaleY(1.02f)
            .setDuration(1000)
            .withEndAction {
                binding.reminderCard.animate()
                    .scaleX(1.0f)
                    .scaleY(1.0f)
                    .setDuration(1000)
                    .withEndAction { startBreathingAnimation() }
                    .start()
            }
            .start()
    }
    
    private fun checkReminderDay() {
        val sharedPref = getSharedPreferences("ReminderPrefs", Context.MODE_PRIVATE)
        val reminderDays = sharedPref.getStringSet("reminder_days", emptySet()) ?: emptySet()
        
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        
        if (reminderDays.contains(today)) {
            // Today is a reminder day
            showReminderAlert()
            playBeepSound()
            performVibration()
        } else {
            // No reminder today
            binding.reminderCard.visibility = View.GONE
            binding.noReminderText.visibility = View.VISIBLE
        }
    }
    
    private fun showReminderAlert() {
        binding.reminderCard.visibility = View.VISIBLE
        binding.noReminderText.visibility = View.GONE
    }
    
    private fun playBeepSound() {
        try {
            // Check if beep_sound resource exists
            val soundResourceId = resources.getIdentifier("beep_sound", "raw", packageName)
            if (soundResourceId != 0) {
                mediaPlayer = MediaPlayer.create(this, soundResourceId)
                mediaPlayer?.setOnCompletionListener { 
                    it.release()
                    mediaPlayer = null
                }
                mediaPlayer?.start()
            } else {
                // Resource doesn't exist, handle gracefully
                Toast.makeText(this, "Звуковой файл недоступен", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Не удалось воспроизвести звук", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun performVibration() {
        vibrator?.let { vibrator ->
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                vibrator.vibrate(500)
            }
        }
    }
    
    private fun showCalendarSelector() {
        val calendarDialog = CalendarSelectorDialog()
        calendarDialog.show(supportFragmentManager, "calendar_selector")
    }
    
    private fun startResultActivity(message: String) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("message", message)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
    
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}