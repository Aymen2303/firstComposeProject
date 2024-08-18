package com.example.userdetailsappcompose.network

import ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface UserService {

    @GET("api/")
    suspend fun listUsers(
        @Query("results") userSize: Int,
    ): ApiResponse

}