package com.example.userdetailsappcompose.network

import retrofit2.Retrofit



class RetrofitInstance {
    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://randomuser.me/api")
        .build()

    var service = retrofit.create(GitHubService::class.java)
}