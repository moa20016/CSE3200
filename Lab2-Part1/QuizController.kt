object QuestionController {
    private val questions = mutableListOf<Question>()
    private var currentQuestionIndex = -1

    fun addQuestion(question: Question) {
        questions.add(question)
    }

    fun nextQuestion(): Question? {
        currentQuestionIndex++
        return if (currentQuestionIndex < questions.size) questions[currentQuestionIndex] else null
    }
}
