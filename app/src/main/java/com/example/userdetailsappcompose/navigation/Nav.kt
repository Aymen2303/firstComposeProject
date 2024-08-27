package com.example.userdetailsappcompose.navigation

import UserListScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.userdetailsappcompose.UserDetailsScreen
import com.example.userdetailsappcompose.network.RetrofitInstance

@Composable
fun Navigation(
    navController: NavHostController,
    retrofitInstance: RetrofitInstance
) {
    NavHost(
        navController = navController,
        startDestination = Route.UserList.route
    ) {
        composable(Route.UserList.route) {
            UserListScreen(
                retrofitInstance = retrofitInstance,
                navController = navController
            )
        }
        composable(
            route = Route.UserDetails.route,
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType },
                navArgument("dob") { type = NavType.StringType },
                navArgument("phone") { type = NavType.StringType },
                navArgument("imageUrl") {type = NavType.StringType}
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val dob = backStackEntry.arguments?.getString("dob") ?: ""
            val phone = backStackEntry.arguments?.getString("phone") ?: ""
            val imageUrl = backStackEntry.arguments?.getString("imageUrl") ?: ""
            UserDetailsScreen(name, email, dob, phone, imageUrl, onBack = { navController.navigateUp() })
        }
    }
}
