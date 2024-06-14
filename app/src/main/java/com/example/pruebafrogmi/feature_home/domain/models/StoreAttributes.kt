package com.example.pruebafrogmi.feature_home.domain.models

import com.example.pruebafrogmi.feature_home.data.models.StoreAttributesDTO

data class StoreAttributes(
    val name: String? = "",
    val code: String? = "",
    val active: Boolean,
    val full_address: String? = "",
    val created_at: String? = ""
)

object StoreAttributesDataFactory{

    fun create(dto : StoreAttributesDTO): StoreAttributes{
        return StoreAttributes(
            name = dto.name,
            code = dto.code,
            active = dto.active,
            full_address = dto.fullAddress,
            created_at = dto.createdAt
        )
    }
}
