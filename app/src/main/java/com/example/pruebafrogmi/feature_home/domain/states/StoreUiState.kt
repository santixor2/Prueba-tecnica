package com.example.pruebafrogmi.feature_home.domain.states

import com.example.pruebafrogmi.feature_home.domain.models.Links
import com.example.pruebafrogmi.feature_home.domain.models.Store

data class StoreUiState(
    val store : List<Store> = listOf(),
    val links : Links? = null,
    val isLoading : Boolean = false
)
