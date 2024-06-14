package com.example.pruebafrogmi.feature_home.data.models

import com.google.gson.annotations.SerializedName

data class LinksDTO(
    @SerializedName("prev")
    val prev: String? ="",
    @SerializedName("next")
    val next: String? ="",
    @SerializedName("first")
    val first: String? = "",
    @SerializedName("last")
    val last: String? = "",
    @SerializedName("self")
    val self: String? = ""
)
