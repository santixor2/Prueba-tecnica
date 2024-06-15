package com.example.pruebafrogmi.feature_home.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StoreDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertStore(storeList : StoreEntity)

    @Query("SELECT * FROM store_data")
    fun getStore() : List<StoreEntity>

    @Query("DELETE FROM store_data")
    fun deleteAllStores()

}