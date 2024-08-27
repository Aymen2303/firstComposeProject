import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.userdetailsappcompose.CardUserDetails
import com.example.userdetailsappcompose.navigation.Route
import com.example.userdetailsappcompose.network.RetrofitInstance

@Composable
fun UserListScreen(
    retrofitInstance: RetrofitInstance,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val refreshTrigger = rememberSaveable { mutableStateOf(false) }

    val users = rememberMutableStateListOf<User>()

    val isLoading = rememberSaveable{ mutableStateOf(true) }

    val showAlertDialog = rememberSaveable { mutableStateOf(false) }

    val listState = rememberLazyListState()

    LaunchedEffect(refreshTrigger.value) {
       if (refreshTrigger.value ) {
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
           refreshTrigger.value = false
       }
        isLoading.value = false
    }

    LaunchedEffect(Unit) {
        if (users.isEmpty()) {
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
                                      dob = toDate(user.dob.date),
                                      user.phoneNumber,
                                      imageUrl = Uri.encode(user.picture.large)
                                  )
                               )
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

@Composable
fun <T: Any> rememberMutableStateListOf(vararg elements: User): SnapshotStateList<User> {
    return rememberSaveable(saver = snapshotStateListUserSaver()) {
        elements.toList().toMutableStateList()
    }
}

private fun snapshotStateListUserSaver() = listSaver<SnapshotStateList<User>, Any>(
    save = { stateList ->
        stateList.map { listItem -> listOf(listItem.name.first, listItem.name.last, listItem.email, listItem.location.country, listItem.picture.large, listItem.phoneNumber, listItem.dob.date) } },
    restore = { savedList ->
        SnapshotStateList<User>().apply {
            savedList.forEach { savedItem ->
                val savedItem2 = savedItem as List<*>
                val user = User(
                    name = Name(first = savedItem2[0] as String, last = savedItem2[1] as String),
                    email = savedItem2[2] as String,
                    location = Location(country = savedItem2[3] as String),
                    picture = Picture(large = savedItem2[4] as String),
                    phoneNumber = savedItem2[5] as String,
                    dob = Dob(date = savedItem2[6] as String)
                )
                add(user)
            }
        }
    },
)