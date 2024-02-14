import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    private val userRepository = UserRepository()

    fun loadUserName() {
        _userName.value = userRepository.getUserById(1).name
    }

    fun setUserName(name: String) {
        userRepository.setUserById(1, name)
        loadUserName()
    }
}
