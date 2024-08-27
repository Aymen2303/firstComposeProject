import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.userdetailsappcompose.R
import com.example.userdetailsappcompose.ui.theme.UserDetailsAppComposeTheme
import java.util.Date

@Composable
fun CustomFAB(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentColor: Color = colorResource(id = R.color.selected)
) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = Color.White,
        contentColor = contentColor,
        modifier = modifier.size(56.dp)
    ) {
        Icon(Icons.Rounded.Refresh, contentDescription = "Refresh")
    }

}

@Composable
fun CustomAlertDialog(
   title: String,
   message: String,
   onDismissRequest : () -> Unit,
   onConfirm: () -> Unit,
   dismissible: Boolean = true,
){
    AlertDialog(
        onDismissRequest = {
            if (dismissible) onDismissRequest()
        },
        title = { Text(text = title) },
        text = { Text(text = message) },
        confirmButton = {
            ElevatedButton(
                onClick = onConfirm,
                colors =  ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.backgroundButton))
            ) {
                Text(
                    text = "Refresh",
                    style = TextStyle(color = colorResource(id = R.color.selected))
                )
            }
        }
    )
}

@Composable
fun ProgressDialog(){
    CircularProgressIndicator(
        modifier = Modifier.size(64.dp),
        color = colorResource(id = R.color.selected),
        trackColor = colorResource(id = R.color.LightColor)
    )
}

@Composable
fun UserDobElevatedCard(
    dateOfBirth : String,
    modifier : Modifier
    ){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier.size(width = 250.dp, height = 75.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.backgroundButton))
        ){
            Row(
                modifier = Modifier
                    .padding(15.dp)
                    .align(Alignment.CenterStart),
                verticalAlignment = Alignment.CenterVertically
            ){
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Date of birth",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily.SansSerif
                        ),
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Text(
                        text = dateOfBirth,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily.SansSerif
                        ),
                        color = colorResource(id = R.color.hintText)
                    )
                }
            }
        }
    }
}

@Composable
fun UserInformationsBox(
    name : String,
    email : String,
    phoneNumber : String
){
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "User Informations",
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = FontFamily.SansSerif
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Full Name",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = colorResource(id = R.color.black)
                )
            )
            Spacer(modifier = Modifier.width(80.dp))

            Text(
                text = name,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = colorResource(id = R.color.hintText)
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        CustomDivider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "E-mail",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = colorResource(id = R.color.black)
                )
            )
            Spacer(modifier = Modifier.width(100.dp))

            Text(
                text = email,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = colorResource(id = R.color.hintText)
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        CustomDivider()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Phone",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = colorResource(id = R.color.black)
                )
            )
            Spacer(modifier = Modifier.width(100.dp))

            Text(
                text = phoneNumber,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = colorResource(id = R.color.hintText)
            )
        }
    }
}

@Composable
fun CustomDivider(
    color : Color = colorResource(id = R.color.LightColor),
    thickness : Dp = 1.dp,
    modifier : Modifier = Modifier
){
    Divider(
        color = color,
        thickness = thickness,
        modifier = modifier
    )
}

@Preview
@Composable
fun viewUserINFOBox(){
    UserDetailsAppComposeTheme {
        UserInformationsBox(
            name = "jjjh kjhiji",
            email = "jiji@gmial.com",
            phoneNumber = "0524524")
    }
}
