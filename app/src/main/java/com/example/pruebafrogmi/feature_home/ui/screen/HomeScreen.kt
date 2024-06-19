package com.example.pruebafrogmi.feature_home.ui.screen

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.example.pruebafrogmi.R
import com.example.pruebafrogmi.feature_home.data.local.StoreDataOffline
import com.example.pruebafrogmi.feature_home.domain.models.Store
import com.example.pruebafrogmi.feature_home.domain.states.StoreUiState
import com.example.pruebafrogmi.feature_home.ui.components.ErrorModal
import com.example.pruebafrogmi.feature_home.ui.components.LoadingImage
import com.example.pruebafrogmi.feature_home.ui.presentation.HomeViewModel
import com.example.pruebafrogmi.util.ConnectivityChecker
import kotlinx.coroutines.flow.filter

@Composable
fun HomeScreen(
    viewmodel: HomeViewModel = hiltViewModel()
) {
    val data by viewmodel.storeUiState.collectAsState()
    val showModal by viewmodel.showModal.collectAsState()
    val contextActivity = LocalContext.current as ComponentActivity
    val context = LocalContext.current
    ConnectivityChecker.initialize(context)
    val isNetworkAvailable by ConnectivityChecker.isNetworkAvailable.collectAsState()
    val dataOff by viewmodel.storeOff.collectAsState()

    LaunchedEffect(Unit) {
        //viewmodel.getStoresDb()
        viewmodel.networkChanges()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Title()
        Spacer(modifier = Modifier.height(20.dp))
        if (isNetworkAvailable){
            StoreList(data, viewmodel)
        }else{
            Text(
                text = "Sin Conexion",
                fontSize = 24.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            OfflineStoreList(storeOff = dataOff)
        }
    }

    if (showModal && isNetworkAvailable){
        ErrorModal(onClickClose = {
            viewmodel.showModal(show = false)
            contextActivity.finish()
        })
    }
    if (data.isLoading){
        LoadingImage()
    }
}

@Composable
fun StoreList(
    data: StoreUiState,
    viewModel: HomeViewModel
) {
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow { listState.isScrolledToTheEnd() }
            .filter { it }
            .collect {
                viewModel.loadMoreStores()
            }
    }

    LazyColumn(state = listState) {
        items(data.store) { store ->
            StoreItem(store = store)
        }
    }
}

@Composable
fun Title() {

    Text(
        text = "Stores",
        fontSize = 24.sp,
        color = Color.Black,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(5.dp))
    Divider(
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth(),
        thickness = 2.dp
    )
}

@Composable
fun StoreItem(store: Store) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(1.dp, Color.Black)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "${store.attributes.name}",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "${store.attributes.code}",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Gray
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "${store.attributes.full_address}",
            fontSize = 18.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun StoreItem2(name : String? = "", code : String? = "", address : String? = "") {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(1.dp, Color.Black)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "$name",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "$code}",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Gray
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "$address",
            fontSize = 18.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun OfflineStoreList(storeOff: List<StoreDataOffline>) {
    LazyColumn {
        items(storeOff) { store ->
            StoreItem2(name = store.name,code = store.code,address = store.address)
        }
    }
}

fun LazyListState.isScrolledToTheEnd() =
    layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1