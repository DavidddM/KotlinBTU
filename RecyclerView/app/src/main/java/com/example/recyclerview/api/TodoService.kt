package com.example.recyclerview.api

import com.example.recyclerview.api.dto.Todo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TodoService {
    @GET("/users/{uID}/todos")
    fun getUserTodos(@Path("uID") uID: Long): Call<List<Todo>>
}