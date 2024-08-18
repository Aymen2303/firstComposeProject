package com.example.userdetailsappcompose

import CustomFAB
import UserListScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.example.userdetailsappcompose.ui.theme.UserDetailsAppComposeTheme
import androidx.compose.ui.res.painterResource
import com.example.userdetailsappcompose.network.RetrofitInstance

class MainActivity : ComponentActivity() {

    private val refreshTrigger = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val retrofitInstance = RetrofitInstance()

        setContent {
            UserDetailsAppComposeTheme {
                BackgroundImageContent {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        floatingActionButton = {
                            CustomFAB(
                                onClick = {
                                    refreshTrigger.value = !refreshTrigger.value
                                }
                            )
                        }
                    ) { innerPadding ->
                        UserListScreen(
                            modifier = Modifier.padding(innerPadding),
                            retrofitInstance = retrofitInstance,
                            refreshTrigger = refreshTrigger
                        )
                    }
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