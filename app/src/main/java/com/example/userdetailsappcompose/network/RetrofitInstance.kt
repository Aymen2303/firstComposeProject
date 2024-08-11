package com.example.userdetailsappcompose.network

import retrofit2.Retrofit

class RetrofitInstance {
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://randomuser.me/api")
        .build()

    var service = retrofit.create(UserService::class.java)
}