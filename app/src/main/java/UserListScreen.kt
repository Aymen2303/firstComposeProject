package com.example.userdetailsappcompose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import Model.User

@Composable
fun UserListScreen(modifier: Modifier = Modifier, userViewModel: UserViewModel) {
    val users by userViewModel.users.collectAsState()
    val user : User
    LazyColumn(modifier = modifier) {
        items(users) { user ->
            CardUserDetails(user)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

