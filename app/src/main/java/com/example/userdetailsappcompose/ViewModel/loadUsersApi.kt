package com.example.userdetailsappcompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.userdetailsappcompose.Model.User
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL
import org.json.JSONObject

class UserViewModel : ViewModel() {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            try {
                val url = "https://randomuser.me/api/?results=10"
                val urlObj = URL(url)
                val connection = urlObj.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connect()

                if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                    val response = connection.inputStream.bufferedReader().readText()
                    val jsonResponse = JSONObject(response)
                    val resultsArray = jsonResponse.getJSONArray("results")

                    val userList = mutableListOf<User>()
                    for (i in 0 until resultsArray.length()) {
                        val userObject = resultsArray.getJSONObject(i)
                        val user = User.fromJson(userObject.toMap())
                        userList.add(user)
                    }
                    _users.value = userList
                } else {
                    println("Couldn't retreive List")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

fun JSONObject.toMap(): Map<String, Any> =
    keys().asSequence().associateWith { key ->
        when (val value = this[key]) {
            is JSONObject -> value.toMap()
            is JSONArray -> value.toList()
            else -> value
        }
    }

fun JSONArray.toList(): List<Any> =
    (0 until length()).map { i ->
        when (val value = this[i]) {
            is JSONObject -> value.toMap()
            is JSONArray -> value.toList()
            else -> value
        }
    }

