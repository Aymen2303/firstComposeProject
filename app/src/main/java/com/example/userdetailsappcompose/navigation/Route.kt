package com.example.userdetailsappcompose.navigation

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Route(val route: String){
    object UserList : Route("user_list")
    object UserDetails : Route("user_details/{name}/{email}/{dob}/{phone}/{imageUrl}"){
            fun CreateRoute(name :String, email : String, dob: String, phone: String, imageUrl: String): String{
                return "user_details/$name/$email/$dob/$phone/$imageUrl"
            }
    }
}