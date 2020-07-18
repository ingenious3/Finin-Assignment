package com.example.fininassignment.network

import com.example.fininassignment.data.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("users")
    fun getUsers(@Query("page") page: Int): Call<ApiResponse>
}