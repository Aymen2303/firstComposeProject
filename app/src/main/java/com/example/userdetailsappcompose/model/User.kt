package com.example.userdetailsappcompose.model

import com.google.gson.annotations.SerializedName

data class User(
    val name: String,
    val email: String,
    val country: String,
    @SerializedName("picture")
    val pictureUrl: String,
    @SerializedName("phone")
    val phoneNumber: String,
    @SerializedName("dob")
    val dateOfBirth: String
)
