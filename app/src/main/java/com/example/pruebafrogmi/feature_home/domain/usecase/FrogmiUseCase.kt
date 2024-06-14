package com.example.pruebafrogmi.feature_home.domain.usecase

import com.example.pruebafrogmi.core.data.ResponseResult
import com.example.pruebafrogmi.feature_home.data.repository.RepositoryImp
import com.example.pruebafrogmi.feature_home.domain.models.ApiResponse
import com.example.pruebafrogmi.feature_home.domain.models.LinksDataFactory
import com.example.pruebafrogmi.feature_home.domain.models.StoreDataFactory
import javax.inject.Inject

class FrogmiUseCase @Inject constructor(private val repository : RepositoryImp) {

    suspend operator fun invoke(page : Int): ResponseResult{
        var responseT : ResponseResult = ResponseResult.Failed(Throwable("no data"))
        kotlin.runCatching {
            repository.getStores(page)
        }.onSuccess { response ->
            val data = ApiResponse(
                data = StoreDataFactory.create2(listDto = response.data),
                links = LinksDataFactory.create(response.links)
            )
            responseT = ResponseResult.Success(data)

        }.onFailure {
            it.printStackTrace()
            responseT = ResponseResult.Failed(it)
        }
        return responseT
    }
}