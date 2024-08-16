import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.text.style.TextAlign
import com.example.userdetailsappcompose.CardUserDetails
import com.example.userdetailsappcompose.network.RetrofitInstance

@Composable
fun UserListScreen(modifier: Modifier = Modifier, retrofitInstance: RetrofitInstance) {
    val users = remember { mutableStateOf<List<User>>(emptyList()) }

    LaunchedEffect(Unit) {
        try {
            val response = retrofitInstance.service.listUsers(10)
            users.value = response.results
            print("users are:${users.value}")
        } catch (e: Exception) {
            Log.e("ApiError", "Error fetching users", e)
        }
    }

    if (users.value.isEmpty()) {
        Text(
            text = "No users to display...",
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center
        )
    } else {
        LazyColumn(modifier = modifier) {
            items(users.value) { user ->
                CardUserDetails(user)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
