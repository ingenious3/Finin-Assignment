package com.example.fininassignment.data.model

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("avatar")
    var avatar: String? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("first_name")
    var firstName: String? = null,
    @SerializedName("id")
    var id: Long? = null,
    @SerializedName("last_name")
    var lastName: String? = null
)