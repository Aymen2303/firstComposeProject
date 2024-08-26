package com.example.userdetailsappcompose

import Picture
import User
import UserDobElevatedCard
import UserInformationsBox
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.userdetailsappcompose.ui.theme.UserDetailsAppComposeTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailsScreen(
    navController: NavController,
    name: String,
    email: String,
    dob: String,
    phone: String,
    imageUrl: String
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                title = { Text("User Details") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = colorResource(id = R.color.selected),
                    titleContentColor = colorResource(id = R.color.white)
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Image(
                    painter = rememberAsyncImagePainter(model = imageUrl),
                    contentDescription = "User profile picture",
                    modifier = Modifier
                        .size(height = 130.dp, width = 124.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .shadow(4.dp, shape = RectangleShape)
                        .align(alignment = Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = name,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(30.dp))

                UserDobElevatedCard(dob)

                Spacer(modifier = Modifier.height(30.dp))

                UserInformationsBox(
                    name = name,
                    email = email,
                    phoneNumber = phone
                )
            }
        }
    )
}

@Preview
@Composable
fun PreviewOfComponent(){
    UserDetailsAppComposeTheme {
        // Use a fake NavController in the preview
        UserDetailsScreen(
            navController = NavController(context = LocalContext.current),
            name = "Joe",
            email = "atGmail@kl.com",
            dob = "1987-05-09",
            phone = "264362436",
            imageUrl = "https://randomuser.me/api/portraits/women/54.jpg"
        )
    }
}
