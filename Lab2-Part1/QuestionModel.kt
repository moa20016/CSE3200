import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuestionViewModel : ViewModel() {

    private val _currentQuestion = MutableLiveData<Question>()
    val currentQuestion: LiveData<Question> = _currentQuestion

    private val questionRepository = QuestionRepository()
    private var currentQuestionId = 1

    fun loadQuestions() {
        _currentQuestion.value = questionRepository.getQuestionById(currentQuestionId)
    }

    fun moveToNextQuestion() {
        currentQuestionId++
        loadQuestions()
    }
}
