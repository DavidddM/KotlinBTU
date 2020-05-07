package com.example.recyclerview.api.dto

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val userName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("website")
    val website: String,
    @SerializedName("phone")
    val phoneNumber: String
)
