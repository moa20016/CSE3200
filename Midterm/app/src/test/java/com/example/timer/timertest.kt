package com.example.timer

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.timer.ui.home.HomeFragment
import com.example.timer.ui.home.HomeViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeFragmentTest {

    @Test
    fun `test formatTime`() {
        val fragment = HomeFragment()
        val formattedTime = fragment.formatTime(3661)
        assertEquals("01:01:01", formattedTime)
    }

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        viewModel = HomeViewModel()
    }

    @Test
    fun testTimerFunctionality() {
        // Start the timer manually
        viewModel.startTimer()

        // Wait for a short period (simulate time passing)
        Thread.sleep(100)

        // Verify that the timer has increased
        assertEquals(1, viewModel.timerValue.value?.toInt())

        // Pause the timer manually
        viewModel.pauseTimer()

        // Wait for another short period
        Thread.sleep(1000)

        // Verify that the timer has not changed
        assertEquals(1, viewModel.timerValue.value?.toInt())

        // Reset the timer manually
        viewModel.resetTimer()

        // Verify that the timer has been reset
        assertEquals(0, viewModel.timerValue.value?.toInt())
    }
}

