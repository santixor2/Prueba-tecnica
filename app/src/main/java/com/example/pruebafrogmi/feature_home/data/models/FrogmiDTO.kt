package com.example.pruebafrogmi.feature_home.data.models

import com.google.gson.annotations.SerializedName

data class FrogmiDTO(
    @SerializedName("data")
    val data : List<StoreDTO>,
    @SerializedName("links")
    val links: LinksDTO
)
