package com.example.userdetailsappcompose.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.userdetailsappcompose.model.User
import com.example.userdetailsappcompose.network.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

class UserViewModel(
    private val retrofitInstance: RetrofitInstance
) : ViewModel() {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    init {
        fetchUsers()
    }


    private fun fetchUsers() {
        viewModelScope.launch {
            try {
                val getUsers = retrofitInstance.service.listUsers(10)

                _users.value = getUsers
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

