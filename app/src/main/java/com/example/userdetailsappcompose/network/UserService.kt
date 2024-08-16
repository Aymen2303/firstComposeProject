package com.example.userdetailsappcompose.network

import ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface UserService {

    @GET("?results=")
    suspend fun listUsers(
        @Query("resultsNum") userSize: Int,
    ): ApiResponse

}