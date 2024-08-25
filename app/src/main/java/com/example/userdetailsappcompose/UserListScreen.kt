import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.userdetailsappcompose.CardUserDetails
import com.example.userdetailsappcompose.navigation.Route
import com.example.userdetailsappcompose.network.RetrofitInstance

@Composable
fun UserListScreen(
    retrofitInstance: RetrofitInstance,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current //debugging purposes only

    val refreshTrigger = remember { mutableStateOf(false) }

    val users = remember { mutableStateListOf<User>() }

    val isLoading = remember { mutableStateOf(false) }

    val showAlertDialog = remember { mutableStateOf(false) }

    val listState = rememberLazyListState()

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
        Box(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading.value) {
                ProgressDialog()
            } else if (users.isEmpty()) {
                Text(
                    text = "No users to display...",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
            } else {
                LazyColumn(state = listState) {
                    itemsIndexed(users) { index, user ->
                        CardUserDetails(
                            user = user,
                            onClick = {
                               navController.navigate(
                                  Route.UserDetails.CreateRoute(
                                      name = "${user.name.first} ${user.name.last}",
                                      user.email,
                                      user.dob.date,
                                      user.phoneNumber
                                  )
                               )
                                Toast.makeText(
                                    context,
                                    "${user.name.first} ${user.name.last} infos are being sent",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        )
                        if(index < users.lastIndex)
                            Spacer(modifier = Modifier.height(8.dp))
                    }
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

