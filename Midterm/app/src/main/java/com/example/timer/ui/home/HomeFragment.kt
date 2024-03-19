package com.example.timer.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.timer.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    lateinit var timer1ViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        timer1ViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        val timerTextView: TextView = binding.timerTextView
        timer1ViewModel.timerValue.observe(viewLifecycleOwner) {
            timerTextView.text = formatTime(it)
        }

        val startButton: Button = binding.startButton
        startButton.setOnClickListener {
            timer1ViewModel.startTimer()
        }

        val pauseButton: Button = binding.pauseButton
        pauseButton.setOnClickListener {
            timer1ViewModel.pauseTimer()
        }

        val resetButton: Button = binding.resetButton
        resetButton.setOnClickListener {
            timer1ViewModel.resetTimer()
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