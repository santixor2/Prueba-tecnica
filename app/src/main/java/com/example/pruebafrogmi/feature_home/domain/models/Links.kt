package com.example.pruebafrogmi.feature_home.domain.models

import com.example.pruebafrogmi.feature_home.data.models.LinksDTO

data class Links(
    val prev: String? = "",
    val next: String? = "",
    val first: String? = "",
    val last: String? = "",
    val self: String? = ""
)

object LinksDataFactory{
    fun create(dto : LinksDTO): Links{
        return Links(
            next = dto.next,
            prev = dto.prev,
            first = dto.first,
            last = dto.last,
            self = dto.self
        )
    }
}
