package life.league.challenge.kotlin.viewmodel

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import life.league.challenge.kotlin.api.Service
import life.league.challenge.kotlin.api.login
import life.league.challenge.kotlin.helper.findUser
import life.league.challenge.kotlin.main.MainActivity
import life.league.challenge.kotlin.model.Post
import life.league.challenge.kotlin.model.PostDetail
import life.league.challenge.kotlin.model.User
import java.util.*
import kotlin.collections.ArrayList

class UserViewModel: ViewModel() {
    private var authToken: String = ""
    private val _posts = MutableLiveData<List<PostDetail>>()
    val posts: LiveData<List<PostDetail>>
        get() = _posts

    companion object {
        const val TAG = "UserViewModel"
    }

    fun fetch() {
        viewModelScope.launch {
            if(authToken == "") {
                fetchAuthToken()
            }
            if(authToken != "") {
                val users = fetchUsers()
                val posts = fetchPosts()
                val result = ArrayList<PostDetail>()
                if(users.isNotEmpty() && posts.isNotEmpty()) {
                    posts.forEach {
                        val user = users.findUser(it.userId)
                        if(user != null) {
                            result.add(PostDetail(it, user))
                        }
                    }
                }

                _posts.value = result
            }
        }
    }

    private suspend fun fetchAuthToken() {
        try {
            val account = Service.api.login("hello", "world")
            authToken = account.apiKey?:""
        } catch (t : Throwable) {
            Log.e(TAG, t.message, t)
        }
    }

    private suspend fun fetchUsers(): List<User> =
        try {
            Service.api.users(authToken)
        } catch (t: Throwable) {
            Collections.emptyList()
        }

    private suspend fun fetchPosts(): List<Post> =
        try {
            Service.api.posts(authToken)
        } catch (t: Throwable) {
            Collections.emptyList()
        }
}