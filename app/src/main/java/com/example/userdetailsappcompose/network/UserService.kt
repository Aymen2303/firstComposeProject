package com.example.userdetailsappcompose.network

import com.example.userdetailsappcompose.model.User
import retrofit2.http.GET
import retrofit2.http.Path


interface UserService {

    @GET("/?results={resultsNum}")
    suspend fun listUsers(
        @Path("resultsNum") userSize: Int,
    ): List<User>

}