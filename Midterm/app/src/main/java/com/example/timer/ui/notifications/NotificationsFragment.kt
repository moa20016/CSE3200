package com.example.timer.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.timer.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!
    private lateinit var timer2ViewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        timer2ViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)

        val timerTextView: TextView = binding.notificationTimerTextView
        timer2ViewModel.timerValue.observe(viewLifecycleOwner) {
            timerTextView.text = formatTime(it)
        }

        val startButton: Button = binding.notificationStartButton
        startButton.setOnClickListener {
            timer2ViewModel.startTimer()
        }

        val pauseButton: Button = binding.notificationPauseButton
        pauseButton.setOnClickListener {
            timer2ViewModel.pauseTimer()
        }

        val resetButton: Button = binding.notificationResetButton
        resetButton.setOnClickListener {
            timer2ViewModel.resetTimer()
        }

        return root
    }

    fun formatTime(timeInSeconds: Long): String {
        val hours = timeInSeconds / 3600
        val minutes = (timeInSeconds % 3600) / 60
        val seconds = timeInSeconds % 60
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}