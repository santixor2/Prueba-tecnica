package com.example.pruebafrogmi.feature_home.domain.models

import com.example.pruebafrogmi.feature_home.data.models.StoreDTO

data class Store(
    val id: String? = "",
    val type: String? = "",
    val attributes: StoreAttributes,
)

object StoreDataFactory {

    fun create(dto : StoreDTO):Store{
        return Store(
            id = dto.id,
            type = dto.type,
            attributes = StoreAttributesDataFactory.create(dto.attributes)
        )
    }

    fun create2(listDto : List<StoreDTO>) : List<Store>{
        return listDto.map { item -> create(item) }
    }
}
