package com.example.myapplication
import com.example.myapplication.controller.ScoreController
import com.example.myapplication.model.Difficulty
import org.junit.Assert.assertEquals
import org.junit.Test

class ScoreTest {

    @Test
    fun testIncrementScore() {
        val scoreController = ScoreController("John")
        scoreController.incrementScore(Difficulty.EASY)
        assertEquals(1.0, scoreController.getScore(), 0.01)

        scoreController.incrementScore(Difficulty.MEDIUM)
        assertEquals(3.0, scoreController.getScore(), 0.01)

        scoreController.incrementScore(Difficulty.HARD)
        assertEquals(6.0, scoreController.getScore(), 0.01)
    }
}
