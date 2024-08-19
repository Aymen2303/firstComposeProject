import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.userdetailsappcompose.R

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
        title = {
            Text(text = title)
        },

        text = {
            Text(text = message)
        },
        confirmButton = {
            ElevatedButton(
                onClick = onConfirm,
                colors =  ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.backgroundButton)
                )
            ) {
                Text(
                    "Refresh",
                    style = TextStyle(
                        color = colorResource(id = R.color.selected)
                    )
                )
            }
        }
    )
}

@Composable
fun ProgressDialogWidget(){
    CircularProgressIndicator(
        modifier = Modifier.size(64.dp),
            color = colorResource(id = R.color.selected ),
        trackColor = colorResource(id = R.color.LightColor )
    )
}
