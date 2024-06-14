package com.example.pruebafrogmi.feature_home.data.repository

import com.example.pruebafrogmi.feature_home.data.models.FrogmiDTO
import com.example.pruebafrogmi.feature_home.data.remote.DataSource
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val dataSource : DataSource) {

    suspend fun getStores(page : Int): FrogmiDTO = dataSource.getStores(page)
}