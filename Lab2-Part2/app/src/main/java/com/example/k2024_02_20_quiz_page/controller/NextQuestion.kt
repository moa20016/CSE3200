package com.example.k2024_02_20_quiz_page.controller

import com.example.k2024_02_20_quiz_page.model.AllQuestions

class NextQuestion {

    private val totalQuestions = AllQuestions().getNumberOfQuestions()

    companion object {
        private var currentQuestion = 0
    }

    fun getQuestionNumber() : Int {
        return currentQuestion
    }
    fun incQuestionNumber() {
        currentQuestion = (currentQuestion +1) % totalQuestions
    }
    fun getNextIncQuestionNumber() : Int {
        incQuestionNumber()
        return currentQuestion
    }

    fun getNextRandomQuestionNumber() : Int {
        val getRand = (0..totalQuestions).random()
        return getRand
    }
}