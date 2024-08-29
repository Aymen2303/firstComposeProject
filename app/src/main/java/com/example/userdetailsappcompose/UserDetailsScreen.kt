package com.example.userdetailsappcompose

import UserDobElevatedCard
import UserInformationsBox
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.userdetailsappcompose.ui.theme.UserDetailsAppComposeTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import com.example.userdetailsappcompose.Values.cornerShape
import com.example.userdetailsappcompose.Values.paddingBody
import com.example.userdetailsappcompose.Values.topAppBarHeight

@Composable
fun UserDetailsScreen(
    name: String,
    email: String,
    dob: String,
    phone: String,
    imageUrl: String,
    onBack: () -> Unit
) {
    val painter = rememberAsyncImagePainter(model = imageUrl)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(
                top = WindowInsets.statusBars
                    .asPaddingValues()
                    .calculateTopPadding()
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = colorResource(id = R.color.selected),
                    shape = RoundedCornerShape(
                        topStart = cornerShape,
                        topEnd = cornerShape
                    )
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(topAppBarHeight)
            ) {
                IconButton(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 4.dp),
                    onClick = onBack
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = "User Details",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = paddingBody,
                        end = paddingBody,
                        bottom = paddingBody + WindowInsets.navigationBars
                            .asPaddingValues()
                            .calculateBottomPadding()
                    )
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(
                            topStart = cornerShape - paddingBody,
                            topEnd = cornerShape - paddingBody,
                            bottomEnd = cornerShape - paddingBody,
                            bottomStart = cornerShape - paddingBody
                        )
                    )
                    .padding(top = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .size(124.dp)
                            .graphicsLayer {
                                shadowElevation = 4.dp.toPx()
                                shape = RoundedCornerShape(18.dp)
                                clip = true
                                translationX = 2f
                                translationY = 4f
                            }
                    ) {
                        Image(
                            painter = painter,
                            contentDescription = "User profile picture",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(16.dp))
                        )
                    }
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item {
                    Text(
                        text = name,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(50.dp))
                    UserDobElevatedCard(
                        dob,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(80.dp))
                    UserInformationsBox(
                        name = name,
                        email = email,
                        phoneNumber = phone
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewOfComponent() {
    UserDetailsAppComposeTheme {
        UserDetailsScreen(
            onBack = {},
            name = "Joe",
            email = "atGmail@kl.com",
            dob = "1987-05-09",
            phone = "264362436",
            imageUrl = "https://randomuser.me/api/portraits/women/54.jpg"
        )
    }
}

internal object Values {
    val topAppBarHeight = 70.dp
    val paddingBody = 4.dp
    val cornerShape = 20.dp
}

