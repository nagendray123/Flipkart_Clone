package com.example.project.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("/users")
    fun getDate(): Call<List<ItemsItem>>
}