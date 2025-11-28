package com.aura.reminder

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.aura.reminder.databinding.DialogCalendarSelectorBinding
import java.text.SimpleDateFormat
import java.util.*

class CalendarSelectorDialog : DialogFragment() {
    private var _binding: DialogCalendarSelectorBinding? = null
    private val binding get() = _binding!!
    
    interface OnDateSelectedListener {
        fun onDateSelected(date: String)
        fun onImmediateReminderCheck(date: String)
    }
    
    var listener: OnDateSelectedListener? = null
    
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogCalendarSelectorBinding.inflate(layoutInflater)
        
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                val selectedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    .format(Date(year - 1900, month, day))
                
                // Save the reminder date
                saveReminderDate(selectedDate)
                
                // Check if selected date is today
                val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
                if (selectedDate == today) {
                    // If it's today, trigger immediate reminder check
                    listener?.onImmediateReminderCheck(selectedDate)
                } else {
                    // Otherwise just notify that date was selected
                    listener?.onDateSelected(selectedDate)
                }
                
                dismiss()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        
        // Set minimum date to today
        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        
        return datePickerDialog
    }
    
    private fun saveReminderDate(date: String) {
        val sharedPref = requireActivity().getSharedPreferences("ReminderPrefs", Context.MODE_PRIVATE)
        val reminderDays = sharedPref.getStringSet("reminder_days", mutableSetOf())?.toMutableSet() ?: mutableSetOf()
        reminderDays.add(date)
        
        sharedPref.edit()
            .putStringSet("reminder_days", reminderDays)
            .apply()
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}