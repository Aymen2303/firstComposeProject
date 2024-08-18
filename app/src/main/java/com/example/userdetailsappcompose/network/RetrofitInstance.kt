package com.example.userdetailsappcompose.network

import ApiResponse
import User
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    val gson = GsonBuilder()
        .registerTypeAdapter(ApiResponse::class.java, JsonDeserializer<ApiResponse> { json, _, _ ->
            val jsonObject = json.asJsonObject
            val resultsJson = jsonObject.get("results").asJsonArray
            val usersList = Gson().fromJson(resultsJson, Array<User>::class.java).toList()
            ApiResponse(usersList)
        })
        .setLenient()
        .create()

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://randomuser.me/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()


    var service = retrofit.create(UserService::class.java)
}