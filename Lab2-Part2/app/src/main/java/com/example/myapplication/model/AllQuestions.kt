package com.example.myapplication.model

class AllQuestions {
    private val allQuestions = arrayListOf<Question<Boolean>>(
        Question<Boolean>("Objective morality can be proven via secular pov", false, Difficulty.MEDIUM),
        Question<Boolean>("11+2 = 21", false, Difficulty.EASY),
        Question<Boolean>("I think therefore I am", true, Difficulty.EASY),
        Question<Boolean>("The scientific method cannot prove consciousness", true, Difficulty.HARD)

    )

    fun getNumberOfQuestions() : Int {
        return allQuestions.size
    }

    fun getQuestion(i: Int) : Question<Boolean>{
        return allQuestions[i]
    }
}