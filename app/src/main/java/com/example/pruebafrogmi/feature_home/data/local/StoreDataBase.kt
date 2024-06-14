package com.example.pruebafrogmi.feature_home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StoreEntity::class], version = 1)
abstract class StoreDataBase : RoomDatabase(){

    abstract fun getStoreDao() : StoreDAO
}