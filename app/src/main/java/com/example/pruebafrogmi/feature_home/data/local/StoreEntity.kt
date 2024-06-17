package com.example.pruebafrogmi.feature_home.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "store_data")
data class StoreEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name : String? = "",
    val code : String? = "",
    val address : String? = "",
    val synced : Boolean = false
)
