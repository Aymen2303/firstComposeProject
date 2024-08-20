package com.example.userdetailsappcompose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UserDetailsScreen(
    name: String,
    email: String,
    dob: String,
    phone: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Name: $name")
        Text(text = "Email: $email")
        Text(text = "Date of Birth: $dob")
        Text(text = "Phone: $phone")
    }
}
