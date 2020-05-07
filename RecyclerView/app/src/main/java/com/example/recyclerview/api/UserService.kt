package com.example.recyclerview.api

import com.example.recyclerview.api.dto.User
import retrofit2.Call
import retrofit2.http.GET

interface UserService {
    @GET("/users")
    fun getAllUsers(): Call<List<User>>
}