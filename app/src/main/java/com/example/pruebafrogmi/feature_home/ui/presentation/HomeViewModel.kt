package com.example.pruebafrogmi.feature_home.ui.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebafrogmi.core.data.ResponseResult
import com.example.pruebafrogmi.feature_home.data.local.StoreDAO
import com.example.pruebafrogmi.feature_home.data.local.StoreDataOffline
import com.example.pruebafrogmi.feature_home.data.local.StoreEntity
import com.example.pruebafrogmi.feature_home.domain.models.ApiResponse
import com.example.pruebafrogmi.feature_home.domain.models.Links
import com.example.pruebafrogmi.feature_home.domain.models.SaveStore
import com.example.pruebafrogmi.feature_home.domain.models.Store
import com.example.pruebafrogmi.feature_home.domain.states.StoreUiState
import com.example.pruebafrogmi.feature_home.domain.usecase.FrogmiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val storeUseCase: FrogmiUseCase,
    val dao : StoreDAO
) : ViewModel() {

    private var data : ApiResponse? = null
    private var store : List<Store> = listOf()
    private var links : Links? = null
    private var saveStore = SaveStore()
    private val _storeUiState = MutableStateFlow(StoreUiState())
    val storeUiState = _storeUiState.asStateFlow()

    private val _storeOff: MutableStateFlow<List<StoreDataOffline>> = MutableStateFlow(emptyList())
    val storeOff = _storeOff.asStateFlow()

    private val _showModal = MutableStateFlow<Boolean>(false)
    val showModal = _showModal

    private var currentPage = 1

    fun getStores(){
        getStore(currentPage)
    }

    fun setLoading(isLoading : Boolean){
        _storeUiState.update {
            it.copy(isLoading = isLoading)
        }
    }

    private fun getStore(page : Int) = viewModelScope.launch(Dispatchers.Main){
        setLoading(isLoading = true)
        val result = storeUseCase(page)

        when(result){
            is ResponseResult.Failed -> {
                val error = result.error
                Log.d("prueba1","response =====> ${error.message}")
                showModal(show = true)
            }
            is ResponseResult.Success<*> -> {
                data = result.data as ApiResponse
                store = data!!.data
                links = data!!.links
                saveStore.allStores.addAll(store)
                currentPage++
                _storeUiState.update {
                    storeUiState.value.copy(
                        store = saveStore.allStores,
                        links = links
                    )
                }
                setLoading(isLoading = false)
            }
        }
    }

    fun loadMoreStores() {
        setLoading(isLoading = true)
        if (links?.next != null) {
            getStore(currentPage)
            setLoading(isLoading = false)
        }
    }

    fun saveStoreToDb(store: Store){
        viewModelScope.launch(Dispatchers.IO){
            dao.insertStore(
                StoreEntity(
                    name = store.attributes.name,
                    code = store.attributes.code,
                    address = store.attributes.full_address,
                    id = 0
                )
            )
        }
    }

    private fun getStoreToDb(): List<StoreDataOffline>{
        val storeOffList = dao.getStore().map {
            StoreDataOffline(
                name = it.name,
                code = it.code,
                address = it.address
            )
        }
        return storeOffList
    }

    fun getStoresDb(){
        viewModelScope.launch(Dispatchers.IO){
            _storeOff.value = getStoreToDb()
        }
    }

    fun showModal(show: Boolean) {
        _showModal.value = show
    }

}