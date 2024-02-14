import org.junit.Test
import org.junit.Assert.*

class MvcTests {

    @Test
    fun testQuestionModel() {
        val question = Question("What is 2 + 2?", listOf("3", "4", "5"), 1)
        assertEquals("What is 2 + 2?", question.text)
        assertEquals(listOf("3", "4", "5"), question.options)
        assertEquals(1, question.correctAnswer)
    }

    @Test
    fun testQuestionController() {
        val questionController = QuestionController
        val question1 = Question("Q1", listOf("A", "B", "C"), 0)
        val question2 = Question("Q2", listOf("X", "Y", "Z"), 2)
        questionController.addQuestion(question1)
        questionController.addQuestion(question2)

        assertEquals(question1, questionController.nextQuestion())
        assertEquals(question2, questionController.nextQuestion())
        assertNull(questionController.nextQuestion())
    }

    @Test
    fun testScoreModelAndController() {
        val scoreController = ScoreController
        assertEquals(0, scoreController.getScore())

        scoreController.incrementScore()
        assertEquals(1, scoreController.getScore())

        scoreController.incrementScore()
        assertEquals(2, scoreController.getScore())
    }

    @Test
    fun testUserModelAndController() {
        val userController = UserController
        assertEquals("", userController.getUserId())

        userController.setUserId("user123")
        assertEquals("user123", userController.getUserId())
    }
}
