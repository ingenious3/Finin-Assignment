package com.example.fininassignment.data

import androidx.paging.PageKeyedDataSource
import com.example.fininassignment.data.model.User
import com.example.fininassignment.data.model.ApiResponse
import com.example.fininassignment.network.ApiInterface
import com.example.fininassignment.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataManager : PageKeyedDataSource<Int, User>() {

    private var pageOffset :Int = 0

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        val service = ApiService.buildApiService(ApiInterface::class.java)
        val call = service.getUsers(params.key)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.users
                    val key = if (params.key > 1) params.key - 1 else 0
                    responseItems?.let {
                        callback.onResult(responseItems, key)
                    }
                }
            }
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
            }
        })
    }
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, User>) {
        val service = ApiService.buildApiService(ApiInterface::class.java)
        val call = service.getUsers(PAGE_OFFSET)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.users
                    responseItems?.let {
                        pageOffset = pageOffset+1
                        callback.onResult(responseItems, null, pageOffset)
                    }
                }
            }
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
            }
        })
    }
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        val service = ApiService.buildApiService(ApiInterface::class.java)
        val call = service.getUsers(pageOffset)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.users
                    pageOffset = pageOffset+1
                    responseItems?.let {
                        callback.onResult(responseItems, pageOffset)
                    }
                }
            }
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
            }
        })
    }
    companion object {
        const val PAGE_OFFSET = 1
    }
}