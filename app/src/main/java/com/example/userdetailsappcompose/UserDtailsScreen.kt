package com.example.userdetailsappcompose

import Picture
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.userdetailsappcompose.ui.theme.UserDetailsAppComposeTheme
import java.net.URL

@Composable
fun UserDetailsScreen(
    name: String,
    email: String,
    dob: String,
    phone: String,
    imageUrl : String
) {
    val painter = rememberAsyncImagePainter(model = imageUrl)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painter,
            contentDescription = "User profile picture",
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(18.dp))
                .shadow(
                    elevation = 4.dp,
                    shape = RectangleShape,
                    ambientColor = colorResource(id = R.color.black)
                )
        )
        Text(text = "Name: $name")
        Text(text = "Email: $email")
        Text(text = "Date of Birth: $dob")
        Text(text = "Phone: $phone")
    }
}

@Preview
@Composable
fun PreviewOfComponent(){
    UserDetailsAppComposeTheme {
        UserDetailsScreen(
            name = "Joe",
            email = "atGmail@kl.com",
            dob = "1987-05-09",
            phone = "264362436",
            imageUrl ="https://randomuser.me/api/portraits/women/54.jpg"
        )
    }
}