package com.example.userdetailsappcompose.navigation

sealed class Route(val route: String){
    object UserList : Route("user_list")
    object UserDetails : Route("user_details/{name}/{email}/{dob}/{phone}"){
            fun CreateRoute(name :String, email : String, dob: String, phone: String): String{
                    return "user_details/$name/$email/$dob/$phone"
            }
    }
}