package com.example.pruebafrogmi.feature_home.data.models

import com.google.gson.annotations.SerializedName

data class StoreDTO(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("type")
    val type: String? = "",
    @SerializedName("attributes")
    val attributes: StoreAttributesDTO
)
