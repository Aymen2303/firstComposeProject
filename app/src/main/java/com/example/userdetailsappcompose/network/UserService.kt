package com.example.userdetailsappcompose.network

import com.example.userdetailsappcompose.Model.User
import retrofit2.http.GET
import retrofit2.http.Path


interface UserService {

    @GET("/?results={resultsNum}")
    fun listUsers(
        @Path("resultsNum") userSize: Int
    ): List<User>
}

class Test: UserService{
    override fun listUsers(userSize: Int): List<User> {
        TODO("Not yet implemented")
    }
}