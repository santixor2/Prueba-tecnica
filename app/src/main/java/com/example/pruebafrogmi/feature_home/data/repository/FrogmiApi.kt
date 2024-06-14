package com.example.pruebafrogmi.feature_home.data.repository

import com.example.pruebafrogmi.feature_home.data.models.FrogmiDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface FrogmiApi {

    @GET("stores?per_page=10")
    suspend fun getStores(@Query("page") page : Int): FrogmiDTO
}