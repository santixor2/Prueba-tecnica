package com.example.pruebafrogmi.feature_home.data.models

import com.google.gson.annotations.SerializedName

data class StoreAttributesDTO(
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("code")
    val code: String? = "",
    @SerializedName("active")
    val active: Boolean,
    @SerializedName("full_address")
    val fullAddress: String? = "",
    @SerializedName("created_at")
    val createdAt: String? = ""
)
