package com.example.userdetailsappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.example.userdetailsappcompose.ui.theme.UserDetailsAppComposeTheme
import androidx.activity.viewModels
import androidx.compose.ui.res.painterResource
import com.example.userdetailsappcompose.com.example.userdetailsappcompose.UserListScreen

class MainActivity : ComponentActivity() {

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserDetailsAppComposeTheme {
                BackgroundImageContent {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        UserListScreen(
                            modifier = Modifier.padding(innerPadding),
                            userViewModel = userViewModel
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BackgroundImageContent(content: @Composable () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        content()
    }
}