package com.example.fininassignment.data.model

import com.google.gson.annotations.SerializedName

data class ApiResponse (
    @SerializedName("data")
    var users: List<User>? = null,
    @SerializedName("page")
    var page: Int = 0,
    @SerializedName("per_page")
    var perPage: Long = 0,
    @SerializedName("total")
    var total: Long = 0,
    @SerializedName("total_pages")
    var totalPages: Int = 0
)