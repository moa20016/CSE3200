package com.example.k2024_02_20_quiz_page.model

class AllQuestions {
    val allQuestions = arrayListOf<Question<Boolean>>(
        Question<Boolean>("There are 366 days in the year", false, Difficulty.EASY),
        Question<Boolean>("There are 7 seas", true, Difficulty.EASY),
        Question<Boolean>("There are 7 continents", true, Difficulty.EASY)
        )

    fun getNumberOfQuestions() : Int {
        return allQuestions.size
    }

    fun getQuestion(i: Int) : Question<Boolean> {
        return allQuestions[i]
    }

}