package com.example.pruebafrogmi.feature_home.data.remote

import com.example.pruebafrogmi.feature_home.data.repository.FrogmiApi
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlinx.coroutines.withContext

class DataSource @Inject constructor(private val api : FrogmiApi) {

    suspend fun getStores(page : Int) = withContext(Dispatchers.IO){api.getStores(page)}
}