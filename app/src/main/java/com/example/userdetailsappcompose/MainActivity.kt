package com.example.userdetailsappcompose

import UserListScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import com.example.userdetailsappcompose.navigation.Navigation
import com.example.userdetailsappcompose.network.RetrofitInstance
import com.example.userdetailsappcompose.ui.theme.UserDetailsAppComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val retrofitInstance = RetrofitInstance()

        setContent {
            UserDetailsAppComposeTheme {
                val navController = rememberNavController()
                BackgroundImageContent {
                    Navigation(
                        navController = navController,
                        retrofitInstance = retrofitInstance
                    )
                }
            }
        }
    }
}


@Composable
fun BackgroundImageContent(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        content()
    }
}