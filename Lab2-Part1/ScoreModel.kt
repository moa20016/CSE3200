import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel : ViewModel() {

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int> = _score

    private val scoreRepository = ScoreRepository()

    fun loadScore() {
        _score.value = scoreRepository.getScoreForUser(1).value
    }

    fun incrementScore() {
        scoreRepository.incrementScoreForUser(1)
        loadScore()
    }
}
