import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import com.example.userdetailsappcompose.CardUserDetails
import com.example.userdetailsappcompose.network.RetrofitInstance

@Composable
fun UserListScreen(
    retrofitInstance: RetrofitInstance,
    modifier: Modifier = Modifier,
    refreshTrigger: MutableState<Boolean>
) {
    val users = remember { mutableStateListOf<User>() }
    val isLoading = remember { mutableStateOf(false) }
    val showAlertDialog = remember { mutableStateOf(false) }

        LaunchedEffect(refreshTrigger.value) {
            isLoading.value = true
            try {
                val response = retrofitInstance.service.listUsers(10)
                val sortedUsers = response.results.sortedBy { it.name.first }
                users.clear()
                users.addAll(sortedUsers)
                showAlertDialog.value = false
            } catch (e: Exception) {
               showAlertDialog.value = true
                Log.e("ApiError", "Error fetching users", e)
            }
            isLoading.value = false
        }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading.value) {
            ProgressDialogWidget()
        } else if (users.isEmpty()) {
            Text(
                text = "No users to display...",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(users) { user ->
                    CardUserDetails(user)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }

    if (showAlertDialog.value) {
        CustomAlertDialog(
            title = "Alert message!",
            message = "We couldn't retrieve the list of users. Hit the refresh button to try again.",
            onDismissRequest = { showAlertDialog.value = false },
            onConfirm = {
                refreshTrigger.value = !refreshTrigger.value
            }
        )
    }
}

